package com.yura.travel.exception;

public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException(String message) {
        super(message);
    }
}
