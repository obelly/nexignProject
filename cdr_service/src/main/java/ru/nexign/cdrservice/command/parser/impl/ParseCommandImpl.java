package ru.nexign.cdrservice.command.parser.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.cdrservice.command.parser.ParseCommand;
import ru.nexign.cdrservice.exception.FileInteractionException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация сервиса парсинга файлов.
 *
 * @author Lds
 */
@Service
@Slf4j
public class ParseCommandImpl implements ParseCommand<Long> {
    @Override
    public List<Long> process(byte[] fileBytes) {
        List<Long> numbersPhone = new ArrayList<>();
        try (var inputStream = new ByteArrayInputStream(fileBytes);
             var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbersPhone.add(Long.valueOf(line));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException("Произошла ошибка при парсинге файла");
        }

        return numbersPhone;
    }
}
