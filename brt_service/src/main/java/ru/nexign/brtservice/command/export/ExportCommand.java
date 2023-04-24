package ru.nexign.brtservice.command.export;

import java.util.List;

/**
 * Сервис выгрузки файлов.
 *
 */
public interface ExportCommand<T> {

    /**
     * Выгрузка файла.
     *
     * @param modelList список для выгрузки
     * @return файл в виде байтов
     */
    byte[] process(List<T> modelList);
}
