package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.command.parser.ParseCommand;
import ru.nexign.hrsservice.dto.CallDataRecord;
import ru.nexign.hrsservice.dto.CallDataRecordPlus;

import ru.nexign.hrsservice.service.CallService;
import ru.nexign.hrsservice.service.ConsumerService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerServiceImpl implements ConsumerService {
    ParseCommand<CallDataRecord> parseCommand;
    CallService callService;

    @RabbitListener(queues = "new_call_data_record")
    @Override
    public void consume(byte[] file) {
        var callDataRecords = parseCommand.process(file);
        List<CallDataRecordPlus> callDataRecordsPluses = new ArrayList<>();
        for (var callDataRecord : callDataRecords) {
            var callDataRecordPlus = new CallDataRecordPlus();
            callDataRecordPlus.setNumberPhone(callDataRecord.getNumberPhone());
            callDataRecordPlus.setCallType(callDataRecord.getCallType());
            callDataRecordPlus.setStartTime(callDataRecord.getStartTime());
            callDataRecordPlus.setEndTime(callDataRecord.getEndTime());
            callDataRecordPlus.setTariff(callService.getCallByPhone(callDataRecord.getNumberPhone()).getTariff());

            callDataRecordsPluses.add(callDataRecordPlus);
        }
        

//        !!!!!
//        producerService.produce(exportCommand.process(callDataRecordsPluses));
    }

}
