package com.example.weatherapi.data.entity.gson.severalDaysWeathre;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;
import com.google.gson.annotations.SerializedName;

public class GsonSeveralDaysWeatherCityResponse implements ISeveralDaysWeatherCityResponse {
    @SerializedName("name")
    private String name;

    @Override
    public String getName() {
        return name;
    }
}
