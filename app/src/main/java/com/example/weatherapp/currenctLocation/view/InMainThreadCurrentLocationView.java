package com.example.weatherapp.currenctLocation.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class InMainThreadCurrentLocationView implements ICurrentLocationView {
    private final ICurrentLocationView origin;
    private final Handler handler;

    public InMainThreadCurrentLocationView(ICurrentLocationView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void startLocationService() {
        handler.post(origin::startLocationService);
    }

    @Override
    public void setIsSearchingLocationProcess(boolean isProcess) {
        handler.post(() -> origin.setIsSearchingLocationProcess(isProcess));
    }

    @Override
    public void setIsPermissionRequiredError(boolean isError) {
        handler.post(() -> origin.setIsPermissionRequiredError(isError));
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        handler.post(() -> origin.showShortForecastDetails(dm));
    }

    @Override
    public void showForecastDetails(IDeviceLocation deviceLocation) {
        handler.post(() -> origin.showForecastDetails(deviceLocation));
    }
}
