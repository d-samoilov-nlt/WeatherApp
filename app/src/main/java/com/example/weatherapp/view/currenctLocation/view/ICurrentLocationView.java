package com.example.weatherapp.view.currenctLocation.view;

import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface ICurrentLocationView {
    void setIsSearchingLocationProcess(boolean isProcess);

    void setIsPermissionRequiredError(boolean isError);

    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);

    void setIsFavoriteSelected(boolean isSelected);
}
