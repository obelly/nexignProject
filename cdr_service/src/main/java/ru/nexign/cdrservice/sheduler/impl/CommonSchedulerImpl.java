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

    @Scheduled(fixedRate = 240000)
    @Override
    public void executeNumbers() {
        log.info("Запрос списка номеров...");
        producerService.produceGetNumbers();
    }

}
