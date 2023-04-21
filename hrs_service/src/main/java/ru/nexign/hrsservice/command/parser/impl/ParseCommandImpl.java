package ru.nexign.hrsservice.command.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.command.parser.ParseCommand;
import ru.nexign.hrsservice.dto.CallDataRecord;
import ru.nexign.hrsservice.enums.CallTypeEnum;
import ru.nexign.hrsservice.enums.DatePatternEnum;
import ru.nexign.hrsservice.exception.FileInteractionException;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация сервиса парсинга файлов.
 *
 * @author Lds
 */
@Service
@Slf4j
public class ParseCommandImpl implements ParseCommand<CallDataRecord> {

    private static final String SPLIT_REGEX = ", ";

    @Override
    public List<CallDataRecord> process(byte[] fileBytes) {
        List<CallDataRecord> callDataRecordList = new ArrayList<>();
        try (var inputStream = new ByteArrayInputStream(fileBytes);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                var fields = line.split(SPLIT_REGEX);
                var record = new CallDataRecord();
                record.setCallType(CallTypeEnum.getByNumber(fields[0]));
                record.setNumberPhone(fields[1]);
                record.setStartTime(LocalDateTime.parse(fields[2], DatePatternEnum.YYYY_MM_DD_HH_MM_SS_TOGETHER.getFormat()));
                record.setEndTime(LocalDateTime.parse(fields[3], DatePatternEnum.YYYY_MM_DD_HH_MM_SS_TOGETHER.getFormat()));
                callDataRecordList.add(record);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException("Произошла ошибка при парсинге файла");
        }

        return callDataRecordList;
    }
}
