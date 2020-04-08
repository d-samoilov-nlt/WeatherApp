package com.example.weatherapp.searchCity.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public interface ISearchCityPresenter {
    void onLocationIconPressed(boolean isLocationEnabled);

    void onLocationUpdated(IDeviceLocation deviceLocation);

    void onLocationEntered(String location);

    void onViewWeatherPressed();

    void onFavoriteSelected(boolean isSelected);

    void onUtilsTypeSelected(boolean isSelected);
}
