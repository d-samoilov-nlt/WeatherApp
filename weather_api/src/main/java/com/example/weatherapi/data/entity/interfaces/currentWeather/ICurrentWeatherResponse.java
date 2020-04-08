package com.example.weatherapi.data.entity.interfaces.currentWeather;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityCoordinateResponse;

public interface ICurrentWeatherResponse {
    int getResponseCode();

    String getCityName();

    ICityCoordinateResponse getCoordinate();

    ICloudsResponse getClouds();

    IMainResponse getMain();

    IWeatherResponse[] getWeatherList();

    IWindResponse getWind();
}
