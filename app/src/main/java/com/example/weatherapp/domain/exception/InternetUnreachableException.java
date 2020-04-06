package com.example.weatherapp.domain.exception;

public class InternetUnreachableException extends RuntimeException {
    public InternetUnreachableException() {
        super();
    }

    public InternetUnreachableException(String message) {
        super(message);
    }
}
