package com.example.weatherapp.forecastDetails.presenter;

import com.example.weatherapp.data.deviceLocation.IDeviceLocation;

public interface IForecastDetailsPresenter {
    void onCreate(IDeviceLocation deviceLocation);

    void onTodayPressed();

    void onTomorrowPressed();

    void onFiveDaysPressed();
}
