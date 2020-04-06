package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ISnowResponse;
import com.google.gson.annotations.SerializedName;

public class GsonSnowResponse implements ISnowResponse {
    @SerializedName("3h")
    private double snowVolume;

    @Override
    public double getSnowVolume() {
        return snowVolume;
    }
}
