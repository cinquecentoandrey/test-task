package com.cinqucento.engineservicestarter.exception;

public class ProjectEntityNotCreatedException extends BaseException {
    public ProjectEntityNotCreatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
