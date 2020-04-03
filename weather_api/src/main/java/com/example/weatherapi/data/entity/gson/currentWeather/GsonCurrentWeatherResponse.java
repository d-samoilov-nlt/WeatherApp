package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IClouds;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMain;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeather;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWind;
import com.google.gson.annotations.SerializedName;

public class GsonCurrentWeatherResponse implements ICurrentWeatherResponse {

    @SerializedName("weather")
    private GsonWeather[] weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private GsonMain main;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("wind")
    private GsonWind wind;
    @SerializedName("clouds")
    private GsonClouds clouds;
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
    public IClouds getClouds() {
        return clouds;
    }

    @Override
    public IMain getMain() {
        return main;
    }

    @Override
    public IWeather[] getWeatherList() {
        return weather;
    }

    @Override
    public IWind getWind() {
        return wind;
    }
}
