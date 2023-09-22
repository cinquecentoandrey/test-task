package com.cinquecento.filestorage.util;

import com.cinquecento.filestorage.util.exception.FileEntityNotFoundException;
import com.cinquecento.filestorage.util.exception.FileEntityNotSavedException;
import com.cinquecento.filestorage.util.exception.FileEntityNotUpdatedException;
import com.cinquecento.filestorage.util.response.FileEntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class FileEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileEntityNotSavedException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotSavedException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getMessage(),
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileEntityNotFoundException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotFoundException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getMessage(),
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileEntityNotUpdatedException.class)
    private ResponseEntity<FileEntityResponse> handledException(FileEntityNotUpdatedException exception) {
        FileEntityResponse response = new FileEntityResponse(
                exception.getMessage(),
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
