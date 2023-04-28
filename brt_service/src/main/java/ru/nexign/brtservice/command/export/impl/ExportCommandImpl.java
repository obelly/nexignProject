package ru.nexign.brtservice.command.export.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.export.ExportCommand;
import ru.nexign.brtservice.dto.CallDataRecordPlus;
import ru.nexign.brtservice.exception.FileInteractionException;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalTime;
import java.util.List;

/**
 * Реализация сервиса выгрузки файлов.<p>
 * Метод выгружает:<p>
 * - в виде файла cdr_plus.txt в папку cdr_plus_files <p>
 * - массив байт для отправки в hrs service
 */
@Service
@Slf4j
public class ExportCommandImpl implements ExportCommand<CallDataRecordPlus> {
    private static final String FILE_NAME = "cdr_plus_%s.txt";
    private static final String ROOT_DIRECTORY = "brt_service/cdr_plus_files";

    public byte[] process(List<CallDataRecordPlus> callDataRecordPluses) {

        var dir = new File(ROOT_DIRECTORY);
        if (!dir.exists() && !dir.mkdirs()) {
            log.warn("Не удалось создать директорию: {}", ROOT_DIRECTORY);
        }

        var fileName = String.format(FILE_NAME, LocalTime.now());
        var file = new File(dir, fileName);
        var baos = new ByteArrayOutputStream();
        try (var fileWriter = new FileWriter(file);
             var bufferedWriter = new BufferedWriter(fileWriter);
             var streamWriter = new OutputStreamWriter(baos)) {
            for (var cdrPlus : callDataRecordPluses) {
                String cdrPlusString = cdrPlus.toCdrPlusString();
                bufferedWriter.write(cdrPlusString);
                streamWriter.write(cdrPlusString);
            }
            streamWriter.flush();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new FileInteractionException("Произошла ошибка при выгрузке файла");
        }
    }
}
