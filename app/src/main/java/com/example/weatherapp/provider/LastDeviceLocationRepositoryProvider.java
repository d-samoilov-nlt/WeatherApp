package com.example.weatherapp.provider;

import android.content.Context;

import com.example.weatherapp.data.repository.ILastDeviceLocationRepository;
import com.example.weatherapp.dataSharedPref.repository.SharedPrefLastDeviceLocationRepository;

public class LastDeviceLocationRepositoryProvider {
    private static ILastDeviceLocationRepository repository;

    public static ILastDeviceLocationRepository get(Context applicationContext) {
        if (repository == null) {
            synchronized (LastDeviceLocationRepositoryProvider.class) {
                if (repository == null) {
                    repository =
                            new SharedPrefLastDeviceLocationRepository(
                                    applicationContext.getSharedPreferences("weather_app", Context.MODE_PRIVATE)
                            );
                }
            }
        }

        return repository;
    }
}
