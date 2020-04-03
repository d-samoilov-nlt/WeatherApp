package com.example.weatherapp.domain.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String s) {
        super(s);
    }
}
