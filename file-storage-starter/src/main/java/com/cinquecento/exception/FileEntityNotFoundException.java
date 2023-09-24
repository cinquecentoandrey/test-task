package com.cinquecento.exception;

public class FileEntityNotFoundException extends BaseException {
    public FileEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
