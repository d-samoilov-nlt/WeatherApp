package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IClouds;
import com.google.gson.annotations.SerializedName;

public class GsonClouds implements IClouds {
    @SerializedName("all")
    private int all;

    @Override
    public int getAll() {
        return all;
    }
}
