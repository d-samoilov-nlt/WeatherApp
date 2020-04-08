package com.example.weatherapp.view.forecastDetails.fragment.model.mapper;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.data.model.forecast.fullForecast.IDayForecastDisplayModel;

public interface IDayForecastMapper {
    IDayForecastDisplayModel map(ISeveralDaysWeatherResponse response);
}
