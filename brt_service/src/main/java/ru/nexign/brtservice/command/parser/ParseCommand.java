package ru.nexign.brtservice.command.parser;

import java.util.List;

/**
 * Сервис парсинга файлов.
 */
public interface ParseCommand<T> {

    /**
     * Парсинг файла.
     *
     * @param file файл в байтах
     * @return список сущностей
     */
    List<T> process(byte[] file);
}
