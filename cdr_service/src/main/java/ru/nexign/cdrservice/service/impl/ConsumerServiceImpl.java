package ru.nexign.cdrservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nexign.cdrservice.service.ConsumerService;
import ru.nexign.cdrservice.service.FileService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsumerServiceImpl implements ConsumerService {

    FileService fileService;

    @RabbitListener(queues = "abonent_numbers_answer")
    @Override
    public void consumePhoneNumbers(List<String> phoneNumbers) {

        fileService.generateFileWithCallDataRecords(phoneNumbers);
        log.info("Список номеров передан в генератор файла cdr");
    }
}
