package com.cinqucento.engineservicestarter.exception;

public class CommentEntityNotCreatedException extends BaseException {
    public CommentEntityNotCreatedException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
