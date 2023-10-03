package com.cinqucento.engineservicestarter.exception;

public class ProjectEntityNotUpdatedException extends BaseException {
    public ProjectEntityNotUpdatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
