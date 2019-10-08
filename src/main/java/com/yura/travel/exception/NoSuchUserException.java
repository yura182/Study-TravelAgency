package com.yura.travel.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String errorMessage) {
        super(errorMessage);
    }
}
