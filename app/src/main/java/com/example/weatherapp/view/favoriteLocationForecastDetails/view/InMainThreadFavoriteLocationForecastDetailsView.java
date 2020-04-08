package com.example.weatherapp.view.favoriteLocationForecastDetails.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class InMainThreadFavoriteLocationForecastDetailsView implements IFavoriteLocationForecastDetailsView {
    private final IFavoriteLocationForecastDetailsView origin;
    private final Handler handler;

    public InMainThreadFavoriteLocationForecastDetailsView(IFavoriteLocationForecastDetailsView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showForecastDetails(ICityLocation cityLocation, int unitType) {
        handler.post(() -> origin.showForecastDetails(cityLocation, unitType));
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
