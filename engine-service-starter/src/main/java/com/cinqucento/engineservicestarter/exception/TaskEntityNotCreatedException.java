package com.cinqucento.engineservicestarter.exception;

public class TaskEntityNotCreatedException extends BaseException {
    public TaskEntityNotCreatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
