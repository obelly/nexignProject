package ru.nexign.cdrservice.sheduler.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.nexign.cdrservice.service.FileService;
import ru.nexign.cdrservice.service.ProducerService;
import ru.nexign.cdrservice.sheduler.CommonScheduler;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonSchedulerImpl implements CommonScheduler {

    ProducerService producerService;
    FileService fileService;

    @Scheduled(initialDelay = 20000,fixedRate = 30000) //каждые 30 секунд
    @Override
    public void execute() {
        log.info("Отправка файла...");
        var fileWithCallDataRecords = fileService.generateFileWithCallDataRecords();
        producerService.produce(fileWithCallDataRecords);
    }

    @Scheduled(fixedRate = 60000)
    @Override
    public void executeNumbers() {
        producerService.produceGetNumbers();
    }

}
