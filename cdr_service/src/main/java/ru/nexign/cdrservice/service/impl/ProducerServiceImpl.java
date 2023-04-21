package ru.nexign.cdrservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.nexign.cdrservice.service.ProducerService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    RabbitTemplate rabbitTemplate;

    @Override
    public void produce(byte[] file) {
        rabbitTemplate.convertAndSend("new_call_data_record", file);
        log.info("Файл cdr.txt отправлен в очередь new_call_data_record");
    }

    @Override
    public void produceGetNumbers() {
        rabbitTemplate.send("abonent_numbers", new Message(new byte[1]));
    }

}
