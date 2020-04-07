package com.example.weatherapp.domain.mapper;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IForecastShortDetailsMapper {
    IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response);
}
