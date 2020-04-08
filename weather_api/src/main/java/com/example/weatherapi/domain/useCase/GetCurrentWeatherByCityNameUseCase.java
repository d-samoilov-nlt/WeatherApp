package com.example.weatherapi.domain.useCase;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import static com.example.weatherapi.util.UtilsRequestUrlOptions.getUrlOptions;

public class GetCurrentWeatherByCityNameUseCase implements IGetCurrentWeatherByCityNameUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetCurrentWeatherByCityNameUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ICurrentWeatherResponse get(String cityName) {
        return get(cityName, ForecastUnitsType.CELSIUS.getValue());
    }

    @Override
    public ICurrentWeatherResponse get(String cityName, int unitType) {
        return openWeatherApi.getCurrentWeatherByName(getUrlOptions(cityName, unitType));
    }
}
