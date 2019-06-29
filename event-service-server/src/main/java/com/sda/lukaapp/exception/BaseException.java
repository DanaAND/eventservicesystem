package com.sda.lukaapp.exception;

public abstract class BaseException extends RuntimeException {

    protected String message;

    public abstract String getCode();

    public BaseException(String message) {
        super(message);
    }
}