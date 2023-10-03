package com.cinqucento.engineservicestarter.exception;

public class ProjectEntityNotDeletedException extends BaseException {
    public ProjectEntityNotDeletedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
