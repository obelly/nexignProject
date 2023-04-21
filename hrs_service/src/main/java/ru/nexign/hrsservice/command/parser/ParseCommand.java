package ru.nexign.hrsservice.command.parser;

import java.util.List;

/**
 * Сервис парсинга файлов.
 *
 * @author Lds
 */
public interface ParseCommand<T> {

    /**
     * Парсинг файла.
     *
     * @param file файл в байтах
     * @return список сущностей
     * @author Lds
     */
    List<T> process(byte[] file);
}
