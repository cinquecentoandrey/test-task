package com.cinquecento.authservicestarter.exception;

public class UserEntityNotLoginException extends BaseException {
    public UserEntityNotLoginException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
