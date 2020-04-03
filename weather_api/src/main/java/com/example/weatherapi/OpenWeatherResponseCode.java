package com.example.weatherapi;

public enum OpenWeatherResponseCode {
    SUCCESS(200),
    EXCEED_LIMIT(429);

    private int value;

    OpenWeatherResponseCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
