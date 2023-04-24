package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.service.FileSender;
import ru.nexign.brtservice.service.ProducerService;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileSenderImpl implements FileSender {
    ProducerService producerService;
    @Override
    public void send(byte[] file) {
        producerService.produceCdrPlus(file);
        log.info("файл cdr+ отправлен");
    }
}
