package com.example.weatherapi.domain.useCase.getSeveralDaysForecast;

import com.example.weatherapi.OpenWeatherApiConfig;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import java.util.HashMap;
import java.util.Map;

public class GetSeveralDaysForecastUseCase implements IGetSeveralDaysForecastUseCase {
    private final IOpenWeatherApi openWeatherApi;

    public GetSeveralDaysForecastUseCase(IOpenWeatherApi openWeatherApi) {
        this.openWeatherApi = openWeatherApi;
    }

    @Override
    public ISeveralDaysWeatherResponse get(ICityLocation location) {
        return openWeatherApi.getFiveDaysForecast(getUrlOptions(location));
    }

    private Map<String, String> getUrlOptions(ICityLocation cityLocation) {

        Map<String, String> urlOptions = new HashMap<>();

        urlOptions.put("lat", String.valueOf(cityLocation.getLatitude()));
        urlOptions.put("lon", String.valueOf(cityLocation.getLongitude()));
        urlOptions.put("appid", OpenWeatherApiConfig.API_KEY);

        return urlOptions;
    }
}
