package com.example.weatherapp.forecastDetails.fragment.model.mapper.exception;

public class UnsupportedForecastDateException extends RuntimeException {
    public UnsupportedForecastDateException() {
        super();
    }

    public UnsupportedForecastDateException(String message) {
        super(message);
    }
}
