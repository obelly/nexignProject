package ru.nexign.brtservice.service;


public interface ConsumerService {
    void consumeCdr(byte[] file);

    void consumeNumbersPhone();

    void consumeAbonentMoney(byte[] changeBalanceList);
}
