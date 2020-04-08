package com.example.weatherapp.view.favoriteLocationForecastDetails.view;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IFavoriteLocationForecastDetailsView {
    void showForecastDetails(IDeviceLocation deviceLocation);
    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);

    void setIsFavoriteSelected(boolean isSelected);

    void setLoadingProcess(boolean isLoading);
}
