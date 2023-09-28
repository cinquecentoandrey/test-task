package com.cinquecento.apigatewaystarter.exception;

public class InvalidJWTException extends BaseException{
    public InvalidJWTException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
