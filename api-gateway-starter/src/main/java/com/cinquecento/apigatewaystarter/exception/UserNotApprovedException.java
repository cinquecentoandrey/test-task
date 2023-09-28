package com.cinquecento.apigatewaystarter.exception;

public class UserNotApprovedException extends BaseException {
    public UserNotApprovedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
