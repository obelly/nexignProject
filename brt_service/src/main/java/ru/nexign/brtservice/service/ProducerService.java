package ru.nexign.brtservice.service;

import java.util.List;

public interface ProducerService {
    void produce(byte[] file);

    void producePhoneNumbers(List<Long> phones);
}
