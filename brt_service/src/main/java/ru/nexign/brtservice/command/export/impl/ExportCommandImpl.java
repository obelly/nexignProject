package ru.nexign.brtservice.command.export.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.command.export.ExportCommand;
import ru.nexign.brtservice.dto.CallDataRecord;
import ru.nexign.brtservice.dto.CallDataRecordPlus;
import ru.nexign.brtservice.exception.FileInteractionException;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import static ru.nexign.brtservice.constants.FileConstants.FILE_NAME;


/**
 * Реализация сервиса выгрузки файлов.
 *
 * @author Lds
 */
@Service
@Slf4j
public class ExportCommandImpl implements ExportCommand<CallDataRecordPlus> {

    public byte[] process(List<CallDataRecordPlus> callDataRecordPluses) {
        var baos = new ByteArrayOutputStream();
        try (var writer = new OutputStreamWriter(baos)) {
            for (var cdrPlus : callDataRecordPluses) {
                writer.write(cdrPlus.toCdrPlusString());
                writer.write("\n");
            }
            writer.flush();

            return baos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileInteractionException(String.format("Ошибка при создании файла: %s", FILE_NAME));
        }
    }
}
