package com.example.weatherapp.service;

import android.content.Context;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;
import com.example.weatherapp.domain.exception.InternetUnreachableException;
import com.example.weatherapp.util.UtilDeviceNetwork;

import java.util.Map;

public class CheckNetworkAvailabilityOpenWeatherAPI implements IOpenWeatherApi {
    private final IOpenWeatherApi origin;
    private final Context context;


    public CheckNetworkAvailabilityOpenWeatherAPI(IOpenWeatherApi origin, Context context) {
        this.origin = origin;
        this.context = context;
    }

    @Override
    public ICurrentWeatherResponse getCurrentWeatherByName(Map<String, String> options) {
        try {
            return origin.getCurrentWeatherByName(options);
        } catch (Exception e) {
            if (!UtilDeviceNetwork.isDeviceOnline(context)) {
                e.printStackTrace();
                throw new InternetUnreachableException();
            } else {
                throw e;
            }
        }
    }

    @Override
    public ICurrentWeatherResponse getCurrentWeatherByLocation(Map<String, String> options) {
        try {
            return origin.getCurrentWeatherByLocation(options);
        } catch (Exception e) {
            if (!UtilDeviceNetwork.isDeviceOnline(context)) {
                e.printStackTrace();
                throw new InternetUnreachableException();
            } else {
                throw e;
            }
        }
    }

    @Override
    public ISeveralDaysWeatherResponse getFiveDaysForecast(Map<String, String> options) {
        try {
            return origin.getFiveDaysForecast(options);
        } catch (Exception e) {
            if (!UtilDeviceNetwork.isDeviceOnline(context)) {
                e.printStackTrace();
                throw new InternetUnreachableException();
            } else {
                throw e;
            }
        }
    }
}
