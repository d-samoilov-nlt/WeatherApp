package com.example.weatherapi.data.entity.pojo;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

public class CityLocation implements ICityLocation {
    private final double longitude;
    private final double latitude;

    public CityLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
