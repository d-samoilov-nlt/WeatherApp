package com.example.weatherapp.view.locationList.model;

import android.net.Uri;

import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IFavoriteLocationItemDisplayModel {
    Uri getIconUrl();

    IForecastShortDetailsDisplayModel getShortDetailsDisplayModel();
}