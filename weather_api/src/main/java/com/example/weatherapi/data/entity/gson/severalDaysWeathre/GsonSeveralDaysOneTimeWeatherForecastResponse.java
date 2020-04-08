package com.example.weatherapi.data.entity.gson.severalDaysWeathre;

import com.example.weatherapi.data.entity.gson.currentWeather.GsonCloudsResponse;
import com.example.weatherapi.data.entity.gson.currentWeather.GsonMainResponse;
import com.example.weatherapi.data.entity.gson.currentWeather.GsonWeatherResponse;
import com.example.weatherapi.data.entity.gson.currentWeather.GsonWindResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.google.gson.annotations.SerializedName;

public class GsonSeveralDaysOneTimeWeatherForecastResponse implements ISeveralDaysOneTimeWeatherForecastResponse {
    @SerializedName("main")
    private GsonMainResponse main;
    @SerializedName("weather")
    private GsonWeatherResponse[] weatherArray;
    @SerializedName("clouds")
    private GsonCloudsResponse clouds;
    @SerializedName("wind")
    private GsonWindResponse wind;
    @SerializedName("dt_txt")
    private String date;

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public IMainResponse getMain() {
        return main;
    }

    @Override
    public IWeatherResponse[] getWeather() {
        return weatherArray;
    }

    @Override
    public ICloudsResponse getClouds() {
        return clouds;
    }

    @Override
    public IWindResponse getWind() {
        return wind;
    }

}
