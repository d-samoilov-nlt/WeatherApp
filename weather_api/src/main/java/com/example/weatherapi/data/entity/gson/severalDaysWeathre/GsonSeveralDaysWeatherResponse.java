package com.example.weatherapi.data.entity.gson.severalDaysWeathre;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.google.gson.annotations.SerializedName;

public class GsonSeveralDaysWeatherResponse implements ISeveralDaysWeatherResponse {
    @SerializedName("cod")
    private int code;
    @SerializedName("list")
    private GsonSeveralDaysOneTimeWeatherForecastResponse[] forecastList;
    @SerializedName("city")
    private GsonSeveralDaysWeatherCityResponse city;

    @Override
    public int getResponseCode() {
        return code;
    }

    @Override
    public ISeveralDaysWeatherCityResponse getCity() {
        return city;
    }

    @Override
    public ISeveralDaysOneTimeWeatherForecastResponse[] getForecastList() {
        return forecastList;
    }
}
