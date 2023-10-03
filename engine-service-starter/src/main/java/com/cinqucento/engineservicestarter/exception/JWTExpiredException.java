package com.cinqucento.engineservicestarter.exception;

public class JWTExpiredException extends BaseException{
    public JWTExpiredException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
