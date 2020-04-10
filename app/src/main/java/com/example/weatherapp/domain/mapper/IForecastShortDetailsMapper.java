package com.example.weatherapp.domain.mapper;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IForecastShortDetailsMapper {
    IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response);

    IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response, int unitType);

}
