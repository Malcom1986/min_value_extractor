package ru.maksimlitvinov.nutrition_control.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.maksimlitvinov.nutrition_control.exceprion.FileProcessingException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<String> handleEntityNotFoundException(FileProcessingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
