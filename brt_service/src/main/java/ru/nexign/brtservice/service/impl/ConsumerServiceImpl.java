package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.export.ExportCommand;
import ru.nexign.brtservice.command.parser.ParseCommand;
import ru.nexign.brtservice.dto.BillingResponse;
import ru.nexign.brtservice.dto.CallDataRecord;
import ru.nexign.brtservice.dto.CallDataRecordPlus;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.entity.Abonent;
import ru.nexign.brtservice.service.AbonentService;
import ru.nexign.brtservice.service.ConsumerService;
import ru.nexign.brtservice.service.FileSender;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerServiceImpl implements ConsumerService {
    ParseCommand<CallDataRecord> parseCommand;
    ParseCommand<ChangeBalance> changeBalanceParseCommand;
    ExportCommand<CallDataRecordPlus> exportCommand;
    FileSender fileSender;

    AbonentService abonentService;

    @RabbitListener(queues = "new_call_data_record")
    @Override
    public void consumeCdr(byte[] file) {
        var callDataRecords = parseCommand.process(file);
        log.info("cdr файл получен {}", callDataRecords);
        List<CallDataRecordPlus> callDataRecordsPluses = new ArrayList<>();
        for (var callDataRecord : callDataRecords) {
            if (isAuthorizeAbonent(callDataRecord.getNumberPhone())) {
                var callDataRecordPlus = new CallDataRecordPlus();
                callDataRecordPlus.setNumberPhone(callDataRecord.getNumberPhone());

                callDataRecordPlus.setTariffType(abonentService.getAbonentByPhone(
                        callDataRecord.getNumberPhone()).getTariff().getTariffNumber());

                callDataRecordPlus.setCallType(callDataRecord.getCallType());
                callDataRecordPlus.setStartTime(callDataRecord.getStartTime());
                callDataRecordPlus.setEndTime(callDataRecord.getEndTime());

                callDataRecordsPluses.add(callDataRecordPlus);
            }
        }
        var callDataRecordPlusFile = exportCommand.process(callDataRecordsPluses);
        fileSender.send(callDataRecordPlusFile);

    }

    private boolean isAuthorizeAbonent(String phoneNumber) {
        Abonent abonentByPhoneNumber = abonentService.getAbonentByPhone(phoneNumber);
        return abonentByPhoneNumber != null && abonentByPhoneNumber.getBalance() > 0;
    }

    @RabbitListener(queues = "abonent_numbers")
    @Override
    public void consumeNumbersPhone() {
        log.info("Выполняется запрос на список номеров");
        abonentService.getAllAbonentsPhoneAndSend();
    }

    /**
     * Слушатель файла с HRS-сервиса с номером телефона и стоимостью услуг
     * Файл парсится в List
     * С помощью полученных данных, меняется баланс у пользователей.
     */

    @RabbitListener(queues = "abonent_money")
    @Override
    public void consumeAbonentMoney(byte[] changeBalance) {
        abonentService.setUpdated();
        changeBalanceParseCommand.process(changeBalance)
                .forEach(abonentService::changeBalance);
        log.info("Произошли изменения в базе");
    }

}
