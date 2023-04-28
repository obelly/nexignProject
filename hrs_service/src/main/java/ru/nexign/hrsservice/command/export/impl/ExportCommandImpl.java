package ru.nexign.hrsservice.command.export.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.command.export.ExportCommand;
import ru.nexign.hrsservice.dto.ChangeBalance;
import ru.nexign.hrsservice.exception.FileInteractionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;


@Service
@Slf4j
public class ExportCommandImpl implements ExportCommand<ChangeBalance> {

    public byte[] process(List<ChangeBalance> changeBalanceList) {

        var baos = new ByteArrayOutputStream();

        try (var streamWriter = new OutputStreamWriter(baos)) {
            for (var changeBalance : changeBalanceList) {
                String changeBalanceString = changeBalance.toString();
                streamWriter.write(changeBalanceString);
                streamWriter.write(System.lineSeparator());
            }
            streamWriter.flush();
            return baos.toByteArray();

        } catch (IOException e) {
            throw new FileInteractionException("Произошла ошибка при выгрузке файла");
        }
    }
}
