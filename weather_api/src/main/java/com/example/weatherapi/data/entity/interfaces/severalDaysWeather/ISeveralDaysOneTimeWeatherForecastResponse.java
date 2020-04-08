package com.example.weatherapi.data.entity.interfaces.severalDaysWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;

public interface ISeveralDaysOneTimeWeatherForecastResponse {
    String getDate();

    IMainResponse getMain();

    IWeatherResponse[] getWeather();

    ICloudsResponse getClouds();

    IWindResponse getWind();

}
