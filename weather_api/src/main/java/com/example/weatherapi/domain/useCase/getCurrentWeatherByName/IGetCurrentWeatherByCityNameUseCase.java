package com.example.weatherapi.domain.useCase.getCurrentWeatherByName;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;

public interface IGetCurrentWeatherByCityNameUseCase {
    ICurrentWeatherResponse get(String cityName);

    ICurrentWeatherResponse get(String cityName, int unitType);
}
