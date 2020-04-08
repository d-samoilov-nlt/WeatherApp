package com.example.weatherapp.searchCity.view;

public interface ISearchCityView {

    void setViewWeatherBtnEnabled(boolean isEnabled);

    void setDeviceLocationInfo(String locationInfo);

    void showCityNotFoundError(boolean isError);

    void startLocationService();

    void stopLocationService();
}
