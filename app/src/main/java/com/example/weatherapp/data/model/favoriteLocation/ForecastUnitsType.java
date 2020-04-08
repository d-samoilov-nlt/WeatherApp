package com.example.weatherapp.data.model.favoriteLocation;

public enum ForecastUnitsType {
    CELSIUS(10),
    FAHRENHEIT(20);

    private int value;

    ForecastUnitsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
