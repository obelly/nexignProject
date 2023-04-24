package ru.nexign.hrsservice.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nexign.hrsservice.dto.ChangeBalance;
import ru.nexign.hrsservice.service.SenderService;
import ru.nexign.hrsservice.service.ProducerService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SenderServiceServiceImpl implements SenderService<byte[]> {
    ProducerService producerService;

    @Override
    public void send(byte[] changeBalance) {
        producerService.produceAbonentMoney(changeBalance);
        log.info("Информация о тарификации отправлена");
    }
}
