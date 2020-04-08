package com.example.weatherapp.data.model.cityLocation;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

import java.io.Serializable;

public class SerializableCityLocation implements ICityLocation, Serializable {
    private final double longitude;
    private final double latitude;

    public SerializableCityLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public SerializableCityLocation(ICityLocation origin) {
        this.longitude = origin.getLongitude();
        this.latitude = origin.getLatitude();
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
}
