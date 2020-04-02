package com.example.weatherapi.service.exception;

public class ExceedApiCallsLimitException extends RuntimeException {
    public ExceedApiCallsLimitException(String s) {
        super(s);
    }

    public ExceedApiCallsLimitException() {
        super();
    }
}
