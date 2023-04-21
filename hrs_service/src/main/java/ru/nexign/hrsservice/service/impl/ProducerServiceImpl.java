package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.dto.ChangeBalance;
import ru.nexign.hrsservice.service.ProducerService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    RabbitTemplate rabbitTemplate;

    @Override
    public void produceAbonentMoney(ChangeBalance changeBalance) {
        rabbitTemplate.convertAndSend("abonent_money", changeBalance);
        log.info("Информация о тарификации отправлена в очередь abonent_money");
    }

}
