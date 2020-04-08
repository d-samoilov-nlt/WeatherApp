package com.example.weatherapi.service.retrofit2;

import com.example.weatherapi.data.entity.gson.currentWeather.GsonCurrentWeatherResponse;
import com.example.weatherapi.data.entity.gson.severalDaysWeathre.GsonSeveralDaysWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.service.exception.RequestFailedException;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;

import java.io.IOException;
import java.util.Map;

import retrofit2.Response;

public class RetrofitToOpenWeatherApiBridge implements IOpenWeatherApi {
    private final IRetrofitOpenWeatherApi retrofitApi;

    public RetrofitToOpenWeatherApiBridge(IRetrofitOpenWeatherApi retrofitApi) {
        this.retrofitApi = retrofitApi;
    }

    @Override
    public ICurrentWeatherResponse getCurrentWeatherByName(Map<String, String> options) {
        try {
            Response<GsonCurrentWeatherResponse> response =
                    retrofitApi.getCurrentWeatherByName(options).execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RequestFailedException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ICurrentWeatherResponse getCurrentWeatherByLocation(Map<String, String> options) {
        try {
            Response<GsonCurrentWeatherResponse> response =
                    retrofitApi.getCurrentWeatherByLocation(options).execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RequestFailedException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ISeveralDaysWeatherResponse getFiveDaysForecast(Map<String, String> options) {
        try {
            Response<GsonSeveralDaysWeatherResponse> response =
                    retrofitApi.getFiveDaysForecast(options).execute();

            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RequestFailedException(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
