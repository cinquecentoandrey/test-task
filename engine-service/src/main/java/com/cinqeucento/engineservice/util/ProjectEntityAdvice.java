package com.cinqeucento.engineservice.util;

import com.cinqucento.engineservicestarter.dto.ProjectEntityResponse;
import com.cinqucento.engineservicestarter.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProjectEntityAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProjectEntityNotCreatedException.class)
    private ResponseEntity<ProjectEntityResponse> handledException(ProjectEntityNotCreatedException exception) {
        ProjectEntityResponse response = new ProjectEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectEntityNotUpdatedException.class)
    private ResponseEntity<ProjectEntityResponse> handledException(ProjectEntityNotUpdatedException exception) {
        ProjectEntityResponse response = new ProjectEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectEntityNotFoundException.class)
    private ResponseEntity<ProjectEntityResponse> handledException(ProjectEntityNotFoundException exception) {
        ProjectEntityResponse response = new ProjectEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectEntityNotDeletedException.class)
    private ResponseEntity<ProjectEntityResponse> handledException(ProjectEntityNotDeletedException exception) {
        ProjectEntityResponse response = new ProjectEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAccessDeniedException.class)
    private ResponseEntity<ProjectEntityResponse> handledException(UserAccessDeniedException exception) {
        ProjectEntityResponse response = new ProjectEntityResponse(
                exception.getErrorCode(),
                exception.getMessage(),
                LocalDateTime.now().toString()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
