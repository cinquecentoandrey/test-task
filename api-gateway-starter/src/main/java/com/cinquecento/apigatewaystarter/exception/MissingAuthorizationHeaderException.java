package com.cinquecento.apigatewaystarter.exception;

public class MissingAuthorizationHeaderException extends BaseException{
    public MissingAuthorizationHeaderException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
