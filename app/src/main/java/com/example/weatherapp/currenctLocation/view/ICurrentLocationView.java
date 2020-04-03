package com.example.weatherapp.currenctLocation.view;

import com.example.weatherapp.data.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface ICurrentLocationView {
    void startLocationService();

    void setIsSearchingLocationProcess(boolean isProcess);

    void setIsPermissionRequiredError(boolean isError);

    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);
}
