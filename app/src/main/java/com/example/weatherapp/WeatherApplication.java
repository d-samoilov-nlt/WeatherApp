package com.example.weatherapp;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.example.weatherapp.domain.useCase.updateFavoriteLocation.EnqueueUpdatingFavoriteLocationWorkerUseCase;
import com.example.weatherapp.service.ConnectivityReceiver;

public class WeatherApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new ConnectivityReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        new EnqueueUpdatingFavoriteLocationWorkerUseCase().enqueue();
    }
}
