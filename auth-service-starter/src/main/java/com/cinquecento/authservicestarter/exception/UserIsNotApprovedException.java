package com.cinquecento.authservicestarter.exception;

public class UserIsNotApprovedException extends BaseException{
    public UserIsNotApprovedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
