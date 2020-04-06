package com.example.weatherapp.forecastDetails.model.mapper;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;

public interface IDayForecastMapper {
    IDayForecastDisplayModel map(ISeveralDaysWeatherResponse response);
}
