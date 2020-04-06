package com.example.weatherapp.provider;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.weatherapi.OpenWeatherApiConfig;
import com.example.weatherapi.service.interfaces.IOpenWeatherApi;
import com.example.weatherapi.service.retrofit2.IRetrofitOpenWeatherApi;
import com.example.weatherapi.service.retrofit2.RetrofitToOpenWeatherApiBridge;
import com.example.weatherapp.service.CheckNetworkAvailabilityOpenWeatherAPI;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenWeatherApiProvider {
    private static final long TIMEOUT_SEC = 60;
    private static IOpenWeatherApi weatherApi;

    public static IOpenWeatherApi get(Context context) {
        if (weatherApi == null) {
            synchronized (OpenWeatherApiProvider.class) {
                if (weatherApi == null) {
                    weatherApi = build(context);
                }
            }
        }

        return weatherApi;
    }

    private static IOpenWeatherApi build(Context context) {
        return
                new CheckNetworkAvailabilityOpenWeatherAPI(
                        new RetrofitToOpenWeatherApiBridge(
                                getCommonRetrofitBuilder()
                                        .client(getCommonClientBuilder().build())
                                        .build()
                                        .create(IRetrofitOpenWeatherApi.class)),
                        context);
    }

    @NonNull
    private static OkHttpClient.Builder getCommonClientBuilder() {
        return
                new OkHttpClient
                        .Builder()
                        .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
                        .writeTimeout(TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    @NonNull
    private static Retrofit.Builder getCommonRetrofitBuilder() {
        return
                new Retrofit.Builder()
                        .baseUrl(OpenWeatherApiConfig.API_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create(
                                        new GsonBuilder()
                                                .setLenient()
                                                .create()));
    }
}
