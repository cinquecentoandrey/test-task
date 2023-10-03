package com.cinqucento.engineservicestarter.exception;

public class IllegalSortArgumentException extends BaseException{
    public IllegalSortArgumentException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
