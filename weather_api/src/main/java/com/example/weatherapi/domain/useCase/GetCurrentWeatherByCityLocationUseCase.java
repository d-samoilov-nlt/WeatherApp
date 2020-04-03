package com.example.weatherapi.domain.useCase;

import com.example.weatherapi.OpenWeatherApiConfig;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import java.util.HashMap;
import java.util.Map;

public class GetCurrentWeatherByCityLocationUseCase implements IGetCurrentWeatherByCityLocationUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetCurrentWeatherByCityLocationUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ICurrentWeatherResponse get(ICityLocation cityLocation) {
        return openWeatherApi.getCurrentWeatherByLocation(getUrlOptions(cityLocation));
    }

    private Map<String, String> getUrlOptions(ICityLocation cityLocation) {

        Map<String, String> urlOptions = new HashMap<>();

        urlOptions.put("lat", String.valueOf(cityLocation.getLatitude()));
        urlOptions.put("lon", String.valueOf(cityLocation.getLongitude()));
        urlOptions.put("appid", OpenWeatherApiConfig.API_KEY);

        return urlOptions;
    }
}
