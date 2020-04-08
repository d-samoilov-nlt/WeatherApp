package com.example.weatherapp.data.model.favoriteLocation;

public enum FavoriteLocationUtilsType {
    CELSIUS(10),
    FAHRENHEIT(20);

    private int value;

    FavoriteLocationUtilsType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
