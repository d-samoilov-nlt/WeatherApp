package com.example.weatherapp.view.currenctLocation.view;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface ICurrentLocationView {
    void startLocationService();

    void stopLocationService();

    void setIsSearchingLocationProcess(boolean isProcess);

    void setIsPermissionRequiredError(boolean isError);

    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);

    void showForecastDetails(ICityLocation cityLocation);

    void setIsFavoriteSelected(boolean isSelected);
}
