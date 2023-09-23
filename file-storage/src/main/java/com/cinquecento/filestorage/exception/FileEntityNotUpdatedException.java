package com.cinquecento.filestorage.exception;

public class FileEntityNotUpdatedException extends BaseException {
    public FileEntityNotUpdatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
