package ru.nexign.brtservice.service;

import java.util.List;

public interface ProducerService {
    void produceCdrPlus(byte[] file);

    void producePhoneNumbers(List<String> phones);
}
