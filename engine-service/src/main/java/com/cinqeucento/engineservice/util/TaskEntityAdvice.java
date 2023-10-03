package com.cinqeucento.engineservice.util;

import com.cinqucento.engineservicestarter.dto.TaskEntityResponse;
import com.cinqucento.engineservicestarter.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TaskEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskEntityNotCreatedException.class)
    private ResponseEntity<TaskEntityResponse> handledException(TaskEntityNotCreatedException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskEntityNotUpdatedException.class)
    private ResponseEntity<TaskEntityResponse> handledException(TaskEntityNotUpdatedException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskEntityNotFoundException.class)
    private ResponseEntity<TaskEntityResponse> handledException(TaskEntityNotFoundException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskEntityNotDeletedException.class)
    private ResponseEntity<TaskEntityResponse> handledException(TaskEntityNotDeletedException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTExpiredException.class)
    private ResponseEntity<TaskEntityResponse> handledException(JWTExpiredException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalSortArgumentException.class)
    private ResponseEntity<TaskEntityResponse> handledException(IllegalSortArgumentException exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalSortDirection.class)
    private ResponseEntity<TaskEntityResponse> handledException(IllegalSortDirection exception) {
        TaskEntityResponse response = new TaskEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
