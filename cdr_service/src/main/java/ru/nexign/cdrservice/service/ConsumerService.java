package ru.nexign.cdrservice.service;

import java.util.List;

public interface ConsumerService {
    void consumePhoneNumbers(List<String> phoneNumbers);
}
