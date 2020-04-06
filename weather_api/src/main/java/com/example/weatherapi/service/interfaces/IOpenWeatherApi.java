package com.example.weatherapi.service.interfaces;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;

import java.util.Map;

public interface IOpenWeatherApi {
    /**
     * GET 2.5/weather
     */
    ICurrentWeatherResponse getCurrentWeatherByName(Map<String, String> options);

    /**
     * GET 2.5/weather
     */
    ICurrentWeatherResponse getCurrentWeatherByLocation(Map<String, String> options);

    /**
     * GET 2.5/forecast
     */
    ISeveralDaysWeatherResponse getFiveDaysForecast(Map<String, String> options);
}
