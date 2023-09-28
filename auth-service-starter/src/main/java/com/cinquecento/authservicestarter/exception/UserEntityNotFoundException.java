package com.cinquecento.authservicestarter.exception;

public class UserEntityNotFoundException extends BaseException {
    public UserEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
