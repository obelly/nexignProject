package ru.nexign.cdrservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.cdrservice.enums.CallTypeEnum;
import ru.nexign.cdrservice.exception.FileInteractionException;
import ru.nexign.cdrservice.service.FileService;
import ru.nexign.cdrservice.service.ProducerService;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService {
    private final Random random = new Random();
    private static final int MIN_LINES = 5;
    private static final int MAX_LINES = 10;
    private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";
    private static final int TIME_UNIT = 60;
    private static final String FILE_NAME = "cdr_%s.txt";
    private static final String ROOT_DIRECTORY = "/Users/ob_so/IdeaProjects/nexignProject/cdr_service/cdr_files";

    ProducerService producerService;

    @Override
    public void generateFileWithCallDataRecords(List<String> phoneNumbers) {
        var dir = new File(ROOT_DIRECTORY);
        if (!dir.exists() && !dir.mkdirs()) {
            log.warn("Не удалось создать директорию: {}", ROOT_DIRECTORY);
        }
        var fileName = String.format(FILE_NAME, LocalTime.now());
        var file = new File(dir, fileName);
        var baos = new ByteArrayOutputStream();

        var linesCount = random.nextInt(MAX_LINES - MIN_LINES + 1) + MIN_LINES;

        try (var fileWriter = new FileWriter(file);
             var bufferedWriter = new BufferedWriter(fileWriter);
             var streamWriter = new OutputStreamWriter(baos)) {
            for (int i = 0; i < linesCount; i++) {
                var writeString = generateLine(phoneNumbers) + System.lineSeparator();
                bufferedWriter.write(writeString);
                streamWriter.write(writeString);
            }
            streamWriter.flush();
            producerService.produceCdr(baos.toByteArray());
        } catch (IOException e) {
            throw new FileInteractionException("Произошла ошибка при выгрузке файла");
        }

    }

    private String generateLine(List<String> phoneNumbers) {
        var dateTime = getRandomDate();
        return (random.nextBoolean() ? CallTypeEnum.INCOMING.getNumber() : CallTypeEnum.OUTGOING.getNumber()) +
                ", " +
                phoneNumbers.get(random.nextInt(phoneNumbers.size())) +
                ", " +
                dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)) +
                ", " +
                dateTime.plusMinutes(random.nextInt(TIME_UNIT))
                        .plusSeconds(random.nextInt(TIME_UNIT))
                        .format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
    private LocalDateTime getRandomDate() {
        LocalDate date = LocalDate.now();
        LocalTime now = LocalTime.now().plusHours(random.nextInt(TIME_UNIT))
                .plusMinutes(random.nextInt(TIME_UNIT))
                .plusSeconds(random.nextInt(TIME_UNIT));
        return LocalDateTime.of(date, now);
    }
}
