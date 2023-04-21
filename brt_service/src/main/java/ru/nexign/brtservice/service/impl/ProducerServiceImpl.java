package ru.nexign.brtservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.nexign.brtservice.service.ProducerService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    RabbitTemplate rabbitTemplate;

    @Override
    public void produce(byte[] file) {
        rabbitTemplate.convertAndSend("call_data_records_plus", file);
        log.info("Файл cdr+.txt отправлен в очередь call_data_records_plus");
    }

    @Override
    public void producePhoneNumbers(List<Long> phones) {
        rabbitTemplate.convertAndSend("abonent_numbers_answer", phones);
        log.info("Список телефонов отправлен в очередь abonent_numbers_answer");
    }


}
