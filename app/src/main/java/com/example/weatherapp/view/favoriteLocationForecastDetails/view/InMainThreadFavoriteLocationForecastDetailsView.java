package com.example.weatherapp.view.favoriteLocationForecastDetails.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class InMainThreadFavoriteLocationForecastDetailsView implements IFavoriteLocationForecastDetailsView {
    private final IFavoriteLocationForecastDetailsView origin;
    private final Handler handler;

    public InMainThreadFavoriteLocationForecastDetailsView(IFavoriteLocationForecastDetailsView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showForecastDetails(IDeviceLocation deviceLocation) {
        handler.post(() -> origin.showForecastDetails(deviceLocation));
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        handler.post(() -> origin.showShortForecastDetails(dm));
    }

    @Override
    public void setIsFavoriteSelected(boolean isSelected) {
        handler.post(() -> origin.setIsFavoriteSelected(isSelected));
    }

    @Override
    public void setLoadingProcess(boolean isLoading) {
        handler.post(() -> origin.setLoadingProcess(isLoading));
    }
}
