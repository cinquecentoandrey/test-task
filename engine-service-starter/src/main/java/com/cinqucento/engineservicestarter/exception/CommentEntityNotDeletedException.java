package com.cinqucento.engineservicestarter.exception;

public class CommentEntityNotDeletedException extends BaseException {
    public CommentEntityNotDeletedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
