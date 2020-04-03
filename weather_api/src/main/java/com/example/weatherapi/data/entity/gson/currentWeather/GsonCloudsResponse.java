package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.google.gson.annotations.SerializedName;

public class GsonCloudsResponse implements ICloudsResponse {
    @SerializedName("all")
    private int all;

    @Override
    public int getAll() {
        return all;
    }
}
