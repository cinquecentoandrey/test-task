package com.cinquecento.filestorage.util;

import com.cinquecento.dto.FileEntityResponse;
import com.cinquecento.exception.FileEntityNotDeletedException;
import com.cinquecento.exception.FileEntityNotFoundException;
import com.cinquecento.exception.FileEntityNotSavedException;
import com.cinquecento.exception.FileEntityNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
public class FileEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileEntityNotSavedException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotSavedException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileEntityNotFoundException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotFoundException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileEntityNotUpdatedException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotUpdatedException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileEntityNotDeletedException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotDeletedException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
