package com.yura.travel.exception;

public class FieldNotCorrectException extends RuntimeException {

    public FieldNotCorrectException(String errorMessage) {
        super(errorMessage);
    }
}
