package ru.nexign.cdrservice.service;

import java.util.List;

public interface FileService {
    byte[] generateFileWithCallDataRecords();

    long getNumber(List<Long> numbers);
}
