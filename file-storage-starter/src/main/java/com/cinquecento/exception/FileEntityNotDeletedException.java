package com.cinquecento.exception;

public class FileEntityNotDeletedException extends BaseException {
    public FileEntityNotDeletedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
