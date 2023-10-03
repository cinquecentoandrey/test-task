package com.cinqucento.engineservicestarter.exception;

public class CommentEntityNotFoundException extends BaseException {
    public CommentEntityNotFoundException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
