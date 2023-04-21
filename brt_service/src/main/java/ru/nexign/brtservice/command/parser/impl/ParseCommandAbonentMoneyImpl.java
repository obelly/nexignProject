package ru.nexign.brtservice.command.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.parser.ParseCommand;
import ru.nexign.brtservice.dto.CallDataRecord;
import ru.nexign.brtservice.enums.CallTypeEnum;
import ru.nexign.brtservice.enums.DatePatternEnum;
import ru.nexign.brtservice.exception.FileInteractionException;
import ru.nexign.brtservice.ro.ChangeBalance;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.nexign.brtservice.constants.FileConstants.SPLIT_REGEX;


/**
 * Реализация сервиса парсинга файла с сервиса HRS.
 *
 * @author Lds
 */
@Service
@Slf4j
public class ParseCommandAbonentMoneyImpl implements ParseCommand<ChangeBalance> {

    @Override
    public List<ChangeBalance> process(byte[] fileBytes) {
        List<ChangeBalance> changeBalanceList = new ArrayList<>();
        try (var inputStream = new ByteArrayInputStream(fileBytes);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                var fields = line.split(SPLIT_REGEX);
                var changeBalance = ChangeBalance.builder()
                        .phoneNumber(fields[0])
                        .cost(Double.valueOf(fields[1]))
                        .build();
                changeBalanceList.add(changeBalance);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException("Произошла ошибка при парсинге файла");
        }

        return changeBalanceList;
    }
}
