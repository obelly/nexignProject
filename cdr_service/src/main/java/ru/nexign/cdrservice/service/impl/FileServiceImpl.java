package ru.nexign.cdrservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.cdrservice.enums.CallTypeEnum;
import ru.nexign.cdrservice.service.FileService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService {
    private static final int MIN_LINES = 5;
    private static final int MAX_LINES = 10;
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    private static final String FILE_NAME = "cdr.txt";
    private Random random = new Random();


    @Override
    public byte[] generateFileWithCallDataRecords() {
        var linesCount = new Random().nextInt(MAX_LINES - MIN_LINES + 1) + MIN_LINES;
        File tempFile = null;
        try (var writer = new FileWriter(FILE_NAME)) {
            for (int i = 0; i < linesCount; i++) {
                writer.write(generateLine());
                writer.write(System.lineSeparator());
            }
            writer.close();
            tempFile = new File(FILE_NAME);
            return Files.readAllBytes(tempFile.toPath());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Произошла ошибка при создании файла");
        } finally {
            if (tempFile != null) {
                if (!tempFile.delete()) {
                    log.warn("Не удалось удалить временный файл: {}", tempFile.getAbsolutePath());
                }
            }
        }
    }


    private String generateLine() {

        var date = LocalDateTime.now();
        return (random.nextBoolean() ? CallTypeEnum.INCOMING.getNumber() : CallTypeEnum.OUTGOING.getNumber()) +
                ", " +
                "77297341121" +
                ", " +
                date.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) +
                ", " +
                date.plusSeconds(random.nextInt(26) + 5).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

    public long getNumber(List<Long> numbers) {
        return numbers.get(random.nextInt(numbers.size()));
    }
}
