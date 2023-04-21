package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.export.ExportCommand;
import ru.nexign.brtservice.command.parser.ParseCommand;
import ru.nexign.brtservice.dto.CallDataRecord;
import ru.nexign.brtservice.dto.CallDataRecordPlus;
import ru.nexign.brtservice.ro.ChangeBalance;
import ru.nexign.brtservice.service.AbonentService;
import ru.nexign.brtservice.service.ConsumerService;
import ru.nexign.brtservice.service.ProducerService;

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
    ProducerService producerService;
    AbonentService abonentService;



//    @RabbitListener(queues = "new_call_data_record")
//    @Override
//    public void consume(byte[] file) {
//        var callDataRecords = parseCommand.process(file);
//        List<CallDataRecordPlus> callDataRecordsPluses = new ArrayList<>();
//        for (var callDataRecord : callDataRecords) {
//            var callDataRecordPlus = new CallDataRecordPlus();
//            callDataRecordPlus.setNumberPhone(callDataRecord.getNumberPhone());
//            callDataRecordPlus.setCallType(callDataRecord.getCallType());
//            callDataRecordPlus.setStartTime(callDataRecord.getStartTime());
//            callDataRecordPlus.setEndTime(callDataRecord.getEndTime());
//            callDataRecordPlus.setTariff(abonentService.getAbonentByPhone(callDataRecord.getNumberPhone()).getTariff());
//
//            callDataRecordsPluses.add(callDataRecordPlus);
//        }
//        producerService.produce(exportCommand.process(callDataRecordsPluses));
//    }

    @RabbitListener(queues = "abonent_numbers")
    @Override
    public void consumeNumbersPhone() {
        abonentService.getAllAbonentsPhone();
    }

    /**
     * Слушатель файла с HRS-сервиса с номером телефона и стоимостью услуг
     * Файл парсится в List
     * С помощью, полученных данных меняется баланс у пользователей.
     */
    @RabbitListener(queues = "abonent_money")
    @Override
    public void consumeAbonentMoney(byte[] file) {
        var changeBalanceList = changeBalanceParseCommand.process(file);
        changeBalanceList.forEach(abonentService::changeBalance);
    }

}
