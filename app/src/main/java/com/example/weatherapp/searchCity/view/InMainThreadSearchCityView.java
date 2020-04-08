package com.example.weatherapp.searchCity.view;

import android.os.Handler;
import android.os.Looper;

public class InMainThreadSearchCityView implements ISearchCityView {
    private final ISearchCityView origin;
    private final Handler handler;

    public InMainThreadSearchCityView(ISearchCityView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void setViewWeatherBtnEnabled(boolean isEnabled) {
        handler.post(() -> origin.setViewWeatherBtnEnabled(isEnabled));
    }

    @Override
    public void setDeviceLocationInfo(String locationInfo) {
        handler.post(() -> origin.setDeviceLocationInfo(locationInfo));
    }

    @Override
    public void showCityNotFoundError(boolean isError) {
        handler.post(() -> origin.showCityNotFoundError(isError));
    }

    @Override
    public void startLocationService() {
        handler.post(origin::startLocationService);
    }

    @Override
    public void stopLocationService() {
        handler.post(origin::stopLocationService);
    }
}
