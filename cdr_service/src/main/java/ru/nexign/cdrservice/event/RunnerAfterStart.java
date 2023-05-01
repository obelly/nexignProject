package ru.nexign.cdrservice.event;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.nexign.cdrservice.service.ProducerService;

@Component
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RunnerAfterStart {
    ProducerService producerService;

    @EventListener(ApplicationReadyEvent.class)
    public void executeNumbers(){
        log.info("Запрос списка номеров...");
        producerService.produceGetNumbers();
    }
}
