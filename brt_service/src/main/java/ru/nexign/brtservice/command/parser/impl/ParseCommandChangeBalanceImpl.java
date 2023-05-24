package ru.nexign.brtservice.command.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.parser.ParseCommand;
import ru.nexign.brtservice.dto.ChangeBalance;
import ru.nexign.brtservice.exception.FileInteractionException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация сервиса парсинга файлов.
 * <p>
 */
@Service
@Slf4j
public class ParseCommandChangeBalanceImpl implements ParseCommand<ChangeBalance> {
    public static final String SPLIT_REGEX = ", ";

    @Override
    public List<ChangeBalance> process(byte[] fileBytes) {
        var changeBalanceList = new ArrayList<ChangeBalance>();
        try (var inputStream = new ByteArrayInputStream(fileBytes);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                var fields = line.split(SPLIT_REGEX);
                var build = new ChangeBalance(
                        fields[0],
                        Double.valueOf(fields[1]));
                changeBalanceList.add(build);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException("Произошла ошибка при парсинге файла");
        }

        return changeBalanceList;
    }
}
