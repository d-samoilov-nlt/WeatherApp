package com.example.weatherapp.view.searchCity.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public interface ISearchCityPresenter {
    void onDestroy();

    void onLocationIconPressed(boolean isLocationEnabled);

    void onLocationUpdated(IDeviceLocation deviceLocation);

    void onLocationEntered(String location);

    void onViewWeatherPressed();

    void onFavoriteSelected(boolean isSelected);

    void onUnitTypeSelected(boolean isSelected);
}
