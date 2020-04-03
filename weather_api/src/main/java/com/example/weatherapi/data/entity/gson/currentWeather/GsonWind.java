package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWind;
import com.google.gson.annotations.SerializedName;

public class GsonWind implements IWind {
    @SerializedName("speed")
    private double speed;
    @SerializedName("deg")
    private int degree;

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public int getDegree() {
        return degree;
    }
}
