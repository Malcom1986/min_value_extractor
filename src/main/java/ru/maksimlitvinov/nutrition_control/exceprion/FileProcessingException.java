package ru.maksimlitvinov.nutrition_control.exceprion;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException(String message) {
        super(message);
    }
}
