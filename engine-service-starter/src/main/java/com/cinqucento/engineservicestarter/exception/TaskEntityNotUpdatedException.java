package com.cinqucento.engineservicestarter.exception;

public class TaskEntityNotUpdatedException extends BaseException {
    public TaskEntityNotUpdatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
