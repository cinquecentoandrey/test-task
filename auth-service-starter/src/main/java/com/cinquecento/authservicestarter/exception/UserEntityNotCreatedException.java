package com.cinquecento.authservicestarter.exception;

public class UserEntityNotCreatedException extends BaseException {
    public UserEntityNotCreatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
