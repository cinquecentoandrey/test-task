package com.cinqeucento.engineservice.util;

import com.cinqucento.engineservicestarter.dto.CommentEntityResponse;
import com.cinqucento.engineservicestarter.exception.CommentEntityNotCreatedException;
import com.cinqucento.engineservicestarter.exception.CommentEntityNotDeletedException;
import com.cinqucento.engineservicestarter.exception.CommentEntityNotFoundException;
import com.cinqucento.engineservicestarter.exception.CommentEntityNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CommentEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CommentEntityNotCreatedException.class)
    private ResponseEntity<CommentEntityResponse> handledException(CommentEntityNotCreatedException exception) {
        CommentEntityResponse response = new CommentEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentEntityNotUpdatedException.class)
    private ResponseEntity<CommentEntityResponse> handledException(CommentEntityNotUpdatedException exception) {
        CommentEntityResponse response = new CommentEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentEntityNotFoundException.class)
    private ResponseEntity<CommentEntityResponse> handledException(CommentEntityNotFoundException exception) {
        CommentEntityResponse response = new CommentEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentEntityNotDeletedException.class)
    private ResponseEntity<CommentEntityResponse> handledException(CommentEntityNotDeletedException exception) {
        CommentEntityResponse response = new CommentEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
