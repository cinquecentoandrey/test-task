package com.cinquecento.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

    private Integer errorCode;

    public BaseException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
