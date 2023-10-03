package com.cinqucento.engineservicestarter.exception;

public class CommentEntityNotUpdatedException extends BaseException {
    public CommentEntityNotUpdatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
