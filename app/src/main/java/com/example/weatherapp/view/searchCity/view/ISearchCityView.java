package com.example.weatherapp.view.searchCity.view;

public interface ISearchCityView {

    void setViewWeatherBtnEnabled(boolean isEnabled);

    void setDeviceLocationInfo(String locationInfo);

    void showCityNotFoundError(boolean isError);

    void startLocationService();

    void stopLocationService();

    void setLocationIconEnabled(boolean isEnabled);
}
