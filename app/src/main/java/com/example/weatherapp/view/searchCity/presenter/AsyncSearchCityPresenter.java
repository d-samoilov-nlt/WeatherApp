package com.example.weatherapp.view.searchCity.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncSearchCityPresenter implements ISearchCityPresenter {
    private final ISearchCityPresenter origin;
    private final ExecutorService service;

    public AsyncSearchCityPresenter(ISearchCityPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onDestroy() {
        service.execute(origin::onDestroy);
    }

    @Override
    public void onLocationIconPressed(boolean isLocationEnabled) {
        service.execute(() -> origin.onLocationIconPressed(isLocationEnabled));
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        service.execute(() -> origin.onLocationUpdated(deviceLocation));
    }

    @Override
    public void onLocationEntered(String location) {
        service.execute(() -> origin.onLocationEntered(location));
    }

    @Override
    public void onViewWeatherPressed() {
        service.execute(origin::onViewWeatherPressed);
    }

    @Override
    public void onFavoriteSelected(boolean isSelected) {
        service.execute(() -> origin.onFavoriteSelected(isSelected));
    }

    @Override
    public void onUnitTypeSelected(boolean isSelected) {
        service.execute(() -> origin.onUnitTypeSelected(isSelected));
    }
}
