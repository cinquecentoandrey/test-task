package com.cinqucento.engineservicestarter.exception;

public class UserAccessDeniedException extends BaseException{
    public UserAccessDeniedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
