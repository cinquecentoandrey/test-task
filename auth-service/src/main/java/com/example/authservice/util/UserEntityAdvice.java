package com.example.authservice.util;


import com.cinquecento.authservicestarter.dto.UserEntityResponse;
import com.cinquecento.authservicestarter.exception.UserEntityNotCreatedException;
import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.cinquecento.authservicestarter.exception.UserEntityNotLoginException;
import com.cinquecento.authservicestarter.exception.UserIsNotApprovedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserEntityNotCreatedException.class)
    private ResponseEntity<UserEntityResponse> handledException(UserEntityNotCreatedException exception) {
        UserEntityResponse response = new UserEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserEntityNotLoginException.class)
    private ResponseEntity<UserEntityResponse> handledException(UserEntityNotLoginException exception) {
        UserEntityResponse response = new UserEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserEntityNotFoundException.class)
    private ResponseEntity<UserEntityResponse> handledException(UserEntityNotFoundException exception) {
        UserEntityResponse response = new UserEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsNotApprovedException.class)
    private ResponseEntity<UserEntityResponse> handledException(UserIsNotApprovedException exception) {
        UserEntityResponse response = new UserEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
