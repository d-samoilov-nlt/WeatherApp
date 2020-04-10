package com.example.weatherapp.view.currenctLocation.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncCurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationPresenter origin;
    private final ExecutorService service;

    public AsyncCurrentLocationPresenter(ICurrentLocationPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onDestroy() {
        service.execute(origin::onDestroy);
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        service.execute(() -> origin.onLocationUpdated(deviceLocation));
    }

    @Override
    public void onTrySearchAgainPressed() {
        service.execute(origin::onTrySearchAgainPressed);
    }

    @Override
    public void onAddToFavoritePressed() {
        service.execute(origin::onAddToFavoritePressed);
    }

    @Override
    public void onRefreshPressed() {
        service.execute(origin::onRefreshPressed);
    }

    @Override
    public void onPermissionGranted() {
        service.execute(origin::onPermissionGranted);
    }

    @Override
    public void onPermissionDenied() {
        service.execute(origin::onPermissionDenied);
    }
}
