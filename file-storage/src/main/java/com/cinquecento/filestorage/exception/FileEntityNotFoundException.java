package com.cinquecento.filestorage.exception;

public class FileEntityNotFoundException extends BaseException {
    public FileEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
