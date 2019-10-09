package com.yura.travel.exception;

public class PasswordGenerationException extends RuntimeException {

    public PasswordGenerationException(String errorMessage) {
        super(errorMessage);
    }
}
