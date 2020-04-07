package com.example.weatherapp.data.model.deviceLocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DeviceLocation implements IDeviceLocation {
    private final double longitude;
    private final double latitude;

    public DeviceLocation(double longitude, double latitude) {
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

    @Override
    public boolean equals(@Nullable Object obj) {
        return
                (obj instanceof DeviceLocation)
                        && this.hashCode() == obj.hashCode()
                        && this.toString().equals(obj.toString());
    }

    @Override
    public int hashCode() {
        int result = 0;
        result += 31 * latitude;
        result += 31 * longitude;
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return longitude + "|" + latitude;
    }
}
