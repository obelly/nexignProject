package ru.nexign.brtservice.exception;

/**
 * Ошибка при взаимодействии с файлом.
 *
 */
public class FileInteractionException extends RuntimeException {
    public FileInteractionException(String message) {
        super(message);
    }
}
