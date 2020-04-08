package com.example.weatherapi.domain.useCase;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;

public interface IGetCurrentWeatherByCityLocationUseCase {
    ICurrentWeatherResponse get(ICityLocation cityLocation);

    ICurrentWeatherResponse get(ICityLocation cityLocation, int unitType);
}
