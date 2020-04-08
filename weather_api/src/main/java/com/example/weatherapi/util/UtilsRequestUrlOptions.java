package com.example.weatherapi.util;

import com.example.weatherapi.OpenWeatherApiConfig;
import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

import java.util.HashMap;
import java.util.Map;

public class UtilsRequestUrlOptions {
    public static Map<String, String> getUrlOptions(String cityName, int unitType) {

        Map<String, String> urlOptions = new HashMap<>();

        urlOptions.put("q", cityName);
        urlOptions.put("units", parseUnitType(unitType));
        urlOptions.put("appid", OpenWeatherApiConfig.API_KEY);

        return urlOptions;
    }

    public static Map<String, String> getUrlOptions(ICityLocation cityLocation, int unitType) {

        Map<String, String> urlOptions = new HashMap<>();

        urlOptions.put("lat", String.valueOf(cityLocation.getLatitude()));
        urlOptions.put("lon", String.valueOf(cityLocation.getLongitude()));
        urlOptions.put("units", parseUnitType(unitType));
        urlOptions.put("appid", OpenWeatherApiConfig.API_KEY);

        return urlOptions;
    }

    private static String parseUnitType(int unitType) {
        if (unitType == ForecastUnitsType.CELSIUS.getValue()) {
            return "metric";
        } else if (unitType == ForecastUnitsType.FAHRENHEIT.getValue()) {
            return "imperial";
        } else {
            throw new IllegalStateException("Unsupported ForecastUnitsType - " + unitType);
        }
    }
}
