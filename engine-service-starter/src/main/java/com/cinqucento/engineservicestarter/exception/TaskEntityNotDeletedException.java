package com.cinqucento.engineservicestarter.exception;

public class TaskEntityNotDeletedException extends BaseException {
    public TaskEntityNotDeletedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
