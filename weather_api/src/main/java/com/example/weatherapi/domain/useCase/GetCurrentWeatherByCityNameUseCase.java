package com.example.weatherapi.domain.useCase;

import com.example.weatherapi.OpenWeatherApiConfig;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import java.util.HashMap;
import java.util.Map;

public class GetCurrentWeatherByCityNameUseCase implements IGetCurrentWeatherByCityNameUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetCurrentWeatherByCityNameUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ICurrentWeatherResponse get(String cityName) {
        return openWeatherApi.getCurrentWeatherByName(getUrlOptions(cityName));
    }

    private Map<String, String> getUrlOptions(String cityName) {

        Map<String, String> urlOptions = new HashMap<>();

        urlOptions.put("q", cityName);
        urlOptions.put("appid", OpenWeatherApiConfig.API_KEY);

        return urlOptions;
    }
}
