package com.example.weatherapi.data.entity.interfaces.currentWeather;

public interface ICurrentWeatherResponse {
    int getResponseCode();

    IClouds getClouds();

    IMain getMain();

    IWeather[] getWeatherList();

    IWind getWind();
}
