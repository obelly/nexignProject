package ru.nexign.hrsservice.service;

public interface SenderService<T> {
    void send(T object);
}
