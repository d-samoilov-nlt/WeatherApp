package com.example.weatherapp.view.locationList.model;

import android.net.Uri;

import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class FavoriteLocationItemDisplayModel implements IFavoriteLocationItemDisplayModel {
    private final Uri iconUrl;
    private final IForecastShortDetailsDisplayModel forecastShortDetailsDisplayModel;

    public FavoriteLocationItemDisplayModel(
            Uri iconUrl,
            IForecastShortDetailsDisplayModel forecastShortDetailsDisplayModel) {
        this.iconUrl = iconUrl;
        this.forecastShortDetailsDisplayModel = forecastShortDetailsDisplayModel;
    }

    @Override
    public Uri getIconUrl() {
        return iconUrl;
    }

    @Override
    public IForecastShortDetailsDisplayModel getShortDetailsDisplayModel() {
        return forecastShortDetailsDisplayModel;
    }
}
