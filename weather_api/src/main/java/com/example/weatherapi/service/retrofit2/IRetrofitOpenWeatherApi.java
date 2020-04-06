package com.example.weatherapi.service.retrofit2;

import com.example.weatherapi.data.entity.gson.currentWeather.GsonCurrentWeatherResponse;
import com.example.weatherapi.data.entity.gson.severalDaysWeathre.GsonSeveralDaysWeatherResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IRetrofitOpenWeatherApi {
    @GET("2.5/weather")
    Call<GsonCurrentWeatherResponse> getCurrentWeatherByName(@QueryMap Map<String, String> options);

    @GET("2.5/weather")
    Call<GsonCurrentWeatherResponse> getCurrentWeatherByLocation(@QueryMap Map<String, String> options);

    @GET("2.5/forecast")
    Call<GsonSeveralDaysWeatherResponse> getFiveDaysForecast(@QueryMap Map<String, String> options);
}
