package com.cinquecento.filestorage.exception;

public class FileEntityNotSavedException extends BaseException {
    public FileEntityNotSavedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
