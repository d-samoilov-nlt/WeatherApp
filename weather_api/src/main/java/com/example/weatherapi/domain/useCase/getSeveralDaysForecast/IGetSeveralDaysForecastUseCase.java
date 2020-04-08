package com.example.weatherapi.domain.useCase.getSeveralDaysForecast;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;

public interface IGetSeveralDaysForecastUseCase {
    ISeveralDaysWeatherResponse get(ICityLocation location);

    ISeveralDaysWeatherResponse get(ICityLocation location, int unitType);
}
