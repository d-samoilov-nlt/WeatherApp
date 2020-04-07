package com.example.weatherapp.currenctLocation.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

import java.util.concurrent.ExecutorService;

public class AsyncCurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationPresenter origin;
    private final ExecutorService service;

    public AsyncCurrentLocationPresenter(ICurrentLocationPresenter origin, ExecutorService service) {
        this.origin = origin;
        this.service = service;
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onStart() {
        service.execute(origin::onStart);
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        service.execute(() -> origin.onLocationUpdated(deviceLocation));
    }

    @Override
    public void onTrySearchAgainPressed() {
        service.execute(origin::onTrySearchAgainPressed);
    }
}
