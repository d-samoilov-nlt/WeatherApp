package com.example.weatherapi.data.entity.interfaces.currentWeather;

public interface ICurrentWeatherResponse {
    int getResponseCode();

    String getCityName();

    ICloudsResponse getClouds();

    IMainResponse getMain();

    IWeatherResponse[] getWeatherList();

    IWindResponse getWind();
}
