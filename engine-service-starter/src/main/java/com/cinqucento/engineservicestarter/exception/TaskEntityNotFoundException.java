package com.cinqucento.engineservicestarter.exception;

public class TaskEntityNotFoundException extends BaseException {
    public TaskEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
