package ru.nexign.brtservice.command.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.parser.ParseCommand;
import ru.nexign.brtservice.dto.CallDataRecord;

import ru.nexign.brtservice.enums.CallTypeEnum;
import ru.nexign.brtservice.enums.DatePatternEnum;
import ru.nexign.brtservice.exception.FileInteractionException;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация сервиса парсинга файлов.
 * <p>
 * Парсит файл в List<CallDataRecord>.
 */
@Service
@Slf4j
public class ParseCommandImpl implements ParseCommand<CallDataRecord> {
    public static final String SPLIT_REGEX = ", ";

    @Override
    public List<CallDataRecord> process(byte[] fileBytes) {
        List<CallDataRecord> callDataRecordList = new ArrayList<>();
        try (var inputStream = new ByteArrayInputStream(fileBytes);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                var fields = line.split(SPLIT_REGEX);
                var callDataRecord = new CallDataRecord();
                callDataRecord.setCallType(CallTypeEnum.getByNumber(fields[0]));
                callDataRecord.setNumberPhone(fields[1]);
                callDataRecord.setStartTime(LocalDateTime.parse(fields[2], DatePatternEnum.YYYY_MM_DD_HH_MM_SS_TOGETHER.getFormat()));
                callDataRecord.setEndTime(LocalDateTime.parse(fields[3], DatePatternEnum.YYYY_MM_DD_HH_MM_SS_TOGETHER.getFormat()));

                callDataRecordList.add(callDataRecord);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException("Произошла ошибка при парсинге файла");
        }

        return callDataRecordList;
    }
}
