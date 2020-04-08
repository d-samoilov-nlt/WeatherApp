package com.example.weatherapp.forecastDetails.containerActivity.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class InMainForecastDetailsContainerView implements IForecastDetailsContainerView {
    private final IForecastDetailsContainerView origin;
    private final Handler handler;

    public InMainForecastDetailsContainerView(IForecastDetailsContainerView origin) {
        this.origin = origin;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showForecastDetails(IDeviceLocation deviceLocation) {
        handler.post(() -> origin.showForecastDetails(deviceLocation));
    }

    @Override
    public void updateForecastDetails(IDeviceLocation deviceLocation) {
        handler.post(() -> origin.updateForecastDetails(deviceLocation));

    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        handler.post(() -> origin.showShortForecastDetails(dm));
    }
}
