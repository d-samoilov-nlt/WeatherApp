package com.example.weatherapp.data.deviceLocation;

import java.io.Serializable;

public class SerializableDeviceLocation implements IDeviceLocation, Serializable {
    private final double longitude;
    private final double latitude;

    public SerializableDeviceLocation(IDeviceLocation origin) {
        this.latitude = origin.getLatitude();
        this.longitude = origin.getLongitude();
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
