package com.example.weatherapp.forecastDetails.model.mapper;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.data.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public interface ISeveralDaysForecastMapper {
    ISeveralDaysForecastDisplayModel map(ISeveralDaysWeatherResponse response);
}
