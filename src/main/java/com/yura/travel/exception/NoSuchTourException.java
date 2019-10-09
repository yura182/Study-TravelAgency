package com.yura.travel.exception;

public class NoSuchTourException extends RuntimeException{
    public NoSuchTourException(String message) {
        super(message);
    }
}
