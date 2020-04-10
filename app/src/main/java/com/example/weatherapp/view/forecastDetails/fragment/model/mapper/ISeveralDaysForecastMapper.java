package com.example.weatherapp.view.forecastDetails.fragment.model.mapper;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public interface ISeveralDaysForecastMapper {
    ISeveralDaysForecastDisplayModel map(ISeveralDaysWeatherResponse response);
}
