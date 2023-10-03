package com.cinqucento.engineservicestarter.exception;

public class ProjectEntityNotFoundException extends BaseException {
    public ProjectEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
