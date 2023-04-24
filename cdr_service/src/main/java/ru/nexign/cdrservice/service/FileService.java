package ru.nexign.cdrservice.service;

import java.util.List;

public interface FileService {
    void generateFileWithCallDataRecords(List<String> phoneNumbers);

}
