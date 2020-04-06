package com.example.weatherapi.data.entity.interfaces.severalDaysWeather;

public interface ISeveralDaysWeatherResponse {
    int getResponseCode();

    ISeveralDaysWeatherCityResponse getCity();

    ISeveralDaysOneTimeWeatherForecastResponse[] getForecastList();
}
