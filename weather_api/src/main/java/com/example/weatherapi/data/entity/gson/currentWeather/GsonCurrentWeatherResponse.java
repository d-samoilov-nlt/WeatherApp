package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;
import com.google.gson.annotations.SerializedName;

public class GsonCurrentWeatherResponse implements ICurrentWeatherResponse {

    @SerializedName("weather")
    private GsonWeatherResponse[] weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private GsonMainResponse main;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("wind")
    private GsonWindResponse wind;
    @SerializedName("clouds")
    private GsonCloudsResponse clouds;
    @SerializedName("dt")
    private int dt;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private int responseCode;

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getCityName() {
        return name;
    }

    @Override
    public ICloudsResponse getClouds() {
        return clouds;
    }

    @Override
    public IMainResponse getMain() {
        return main;
    }

    @Override
    public IWeatherResponse[] getWeatherList() {
        return weather;
    }

    @Override
    public IWindResponse getWind() {
        return wind;
    }
}
