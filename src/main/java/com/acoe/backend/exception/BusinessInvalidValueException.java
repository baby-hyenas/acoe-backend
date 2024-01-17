package com.acoe.backend.exception;

public class BusinessInvalidValueException extends RuntimeException {

    public BusinessInvalidValueException(String message) {
        super(message);
    }
}