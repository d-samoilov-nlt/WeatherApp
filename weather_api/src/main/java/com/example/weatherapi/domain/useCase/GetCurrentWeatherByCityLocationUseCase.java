package com.example.weatherapi.domain.useCase;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import static com.example.weatherapi.util.UtilsRequestUrlOptions.getUrlOptions;

public class GetCurrentWeatherByCityLocationUseCase implements IGetCurrentWeatherByCityLocationUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetCurrentWeatherByCityLocationUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ICurrentWeatherResponse get(ICityLocation cityLocation) {
        return get(cityLocation, ForecastUnitsType.CELSIUS.getValue());
    }

    @Override
    public ICurrentWeatherResponse get(ICityLocation cityLocation, int unitType) {
        return openWeatherApi.getCurrentWeatherByLocation(getUrlOptions(cityLocation, unitType));
    }
}
