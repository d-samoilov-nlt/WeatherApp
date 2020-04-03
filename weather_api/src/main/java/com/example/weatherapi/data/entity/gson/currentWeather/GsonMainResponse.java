package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.google.gson.annotations.SerializedName;

public class GsonMainResponse implements IMainResponse {
    @SerializedName("temp")
    private double temp;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;

    @Override
    public double getTemp() {
        return temp;
    }

    @Override
    public int getPressure() {
        return pressure;
    }

    @Override
    public int getHumidity() {
        return humidity;
    }

    @Override
    public double getTempMin() {
        return tempMin;
    }

    @Override
    public double getTempMax() {
        return tempMax;
    }
}
