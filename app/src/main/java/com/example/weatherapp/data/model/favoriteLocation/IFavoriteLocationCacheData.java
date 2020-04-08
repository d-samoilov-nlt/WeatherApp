package com.example.weatherapp.data.model.favoriteLocation;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;

public interface IFavoriteLocationCacheData {
    String getCityName();

    int getForecastUnitType();

    ICurrentWeatherResponse getCurrentWeather();

    ISeveralDaysWeatherResponse getSeveralDaysForecast();
}
