package com.example.weatherapp.view.favoriteLocationForecastDetails.view;

import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IFavoriteLocationForecastDetailsView {

    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);

    void setIsFavoriteSelected(boolean isSelected);

    void setLoadingProcess(boolean isLoading);
}
