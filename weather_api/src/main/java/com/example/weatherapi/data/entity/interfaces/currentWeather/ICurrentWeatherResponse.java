package com.example.weatherapi.data.entity.interfaces.currentWeather;

public interface ICurrentWeatherResponse {
    int getResponseCode();

    String getCityName();

    IClouds getClouds();

    IMain getMain();

    IWeather[] getWeatherList();

    IWind getWind();
}
