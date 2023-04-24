package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import ru.nexign.hrsservice.command.export.ExportCommand;
import ru.nexign.hrsservice.command.parser.ParseCommand;
import ru.nexign.hrsservice.dto.CallDataRecordPlus;
import ru.nexign.hrsservice.dto.ChangeBalance;
import ru.nexign.hrsservice.entity.Call;
import ru.nexign.hrsservice.entity.PayLoad;
import ru.nexign.hrsservice.enums.CallTypeEnum;
import ru.nexign.hrsservice.enums.TariffTypeEnum;
import ru.nexign.hrsservice.service.CallService;
import ru.nexign.hrsservice.service.ConsumerService;
import ru.nexign.hrsservice.service.PayLoadService;
import ru.nexign.hrsservice.service.SenderService;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerServiceImpl implements ConsumerService {
    public static final double ZERO = 0.0;
    public static final int FIRST_MINUTES_REGULAR_TARIFF = 100;
    public static final int FIRST_MINUTES_UNLIMITED_TARIFF = 300;
    public static final int PRICE_UNLIMITED = 100;
    private static final double SECONDS_IN_MINUTE = 60.0;
    private static final String MONETARY_UNIT = "rub";
    CallService callService;
    PayLoadService payLoadService;
    ParseCommand<CallDataRecordPlus> parseCommand;
    SenderService<byte[]> senderService;
    ExportCommand<ChangeBalance> exportCommand;

    @RabbitListener(queues = "call_data_records_plus")
    @Override
    public void consumeCdrPlus(byte[] file) {
        log.info("cdr+ принят");
        List<CallDataRecordPlus> callDataRecordPlusList = parseCommand.process(file);
        List<ChangeBalance> changeBalanceList = new ArrayList<>();

        var mapCallDataRecordsPlus = callDataRecordPlusList.stream()
                .collect(Collectors.groupingBy(CallDataRecordPlus::getNumberPhone));
        mapCallDataRecordsPlus.forEach((numberPhone, callDataRecordPluses) -> {
            Call call = callService.getCallByPhone(numberPhone);

            var tariffType = callDataRecordPluses.stream().findFirst()
                    .orElseThrow(() -> new RuntimeException(String.format("Список с тарифом пустой для номера %s", numberPhone)))
                    .getTariffType();
            call.setTariff(tariffType);

            var payLoads = createPayLoads(callDataRecordPluses, call);
            call.setPayLoads(payLoads);

            var df = new DecimalFormat("0.00");
            df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));

            var sumCost = payLoads.stream().mapToDouble(PayLoad::getCost).sum();
            call.setTotalCost(getTotalCostByTariff(sumCost, call.getTariff()));
            call.setMonetaryUnit(MONETARY_UNIT);
            changeBalanceList.add(ChangeBalance.builder()
                    .numberPhone(numberPhone)
                    .cost(sumCost).build());
            callService.createCall(call);

            log.info("Звонок сохранен в базу");
        });
        byte[] changeBalanceBytes = exportCommand.process(changeBalanceList);
        senderService.send(changeBalanceBytes);
    }

    private List<PayLoad> createPayLoads(List<CallDataRecordPlus> callDataRecordPlusList, Call call) {
        int countUnlimited = 0;
        int countStandard = 0;
        List<PayLoad> payLoads = new ArrayList<>();
        for (var callDataRecordPlus : callDataRecordPlusList) {
            var payLoad = new PayLoad();
            payLoad.setCall(call);
            payLoad.setCallType(callDataRecordPlus.getCallType());
            payLoad.setStartTime(callDataRecordPlus.getStartTime());
            payLoad.setEndTime(callDataRecordPlus.getEndTime());

            var duration = LocalTime.ofSecondOfDay(Duration.between(callDataRecordPlus.getStartTime(), callDataRecordPlus.getEndTime()).getSeconds());
            payLoad.setDuration(duration);

            var totalMinutes = (int) Math.ceil(duration.toSecondOfDay() / SECONDS_IN_MINUTE);

            if (CallTypeEnum.OUTGOING.equals(callDataRecordPlus.getCallType())) {
                switch (callDataRecordPlus.getTariffType()) {
                    case PER_MINUTE -> payLoad.setCost(totalMinutes * TariffTypeEnum.PER_MINUTE.getPriceRubMin());
                    case REGULAR -> {
                        countStandard += totalMinutes;
                        var remainingMinutesRegular = countStandard - FIRST_MINUTES_REGULAR_TARIFF;
                        if (remainingMinutesRegular > ZERO) {
                            var regularTariffMinutes = Math.min(totalMinutes, remainingMinutesRegular);
                            var perMinuteMinutes = totalMinutes - regularTariffMinutes;
                            payLoad.setCost(regularTariffMinutes * TariffTypeEnum.REGULAR.getPriceRubMin() + perMinuteMinutes * TariffTypeEnum.PER_MINUTE.getPriceRubMin());
                        } else {
                            payLoad.setCost(totalMinutes * TariffTypeEnum.REGULAR.getPriceRubMin());
                        }
                    }
                    case UNLIMITED -> {
                        countUnlimited += totalMinutes;
                        if (countUnlimited >= FIRST_MINUTES_UNLIMITED_TARIFF) {
                            var remainingMinutesUnlimited = countUnlimited - FIRST_MINUTES_UNLIMITED_TARIFF;
                            if (remainingMinutesUnlimited > ZERO) {
                                var perMinuteMinutes = totalMinutes - Math.min(totalMinutes, remainingMinutesUnlimited);
                                payLoad.setCost(perMinuteMinutes * TariffTypeEnum.UNLIMITED.getPriceRubMin());
                            } else {
                                payLoad.setCost(totalMinutes * TariffTypeEnum.UNLIMITED.getPriceRubMin());
                            }
                        } else {
                            payLoad.setCost(ZERO);
                        }
                    }
                    default ->
                            throw new RuntimeException(String.format("Неизвестный тариф: %s", callDataRecordPlus.getTariffType()));
                }
            } else {
                payLoad.setCost(ZERO);

            }

            payLoadService.createPayLoad(payLoad);
            payLoads.add(payLoad);
        }
        return payLoads;
    }

    private double getTotalCostByTariff(double sumCost, TariffTypeEnum tariff) {
        return switch (tariff) {
            case PER_MINUTE, REGULAR -> sumCost;
            case UNLIMITED -> sumCost > 0 ? sumCost + PRICE_UNLIMITED : PRICE_UNLIMITED;
        };
    }
}

