package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;
import com.google.gson.annotations.SerializedName;

public class GsonWindResponse implements IWindResponse {
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
