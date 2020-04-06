package com.example.weatherapp.forecastDetails.presenter;

import com.example.weatherapp.data.deviceLocation.IDeviceLocation;

import java.util.concurrent.ExecutorService;

public class AsyncForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;
    private final ExecutorService service;

    public AsyncForecastDetailsPresenter(
            IForecastDetailsPresenter origin, ExecutorService service) {
        this.origin = origin;
        this.service = service;
    }

    @Override
    public void onCreate(IDeviceLocation deviceLocation) {
        service.execute(() -> origin.onCreate(deviceLocation));
    }
}
