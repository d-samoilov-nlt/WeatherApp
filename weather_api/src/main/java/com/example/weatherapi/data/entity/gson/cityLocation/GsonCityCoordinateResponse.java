package com.example.weatherapi.data.entity.gson.cityLocation;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityCoordinateResponse;
import com.google.gson.annotations.SerializedName;

public class GsonCityCoordinateResponse implements ICityCoordinateResponse {
    @SerializedName("lon")
    private double longitude;
    @SerializedName("lat")
    private double latitude;

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
}
