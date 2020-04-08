package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public interface IForecastDetailsPresenter {
    void onCreate(IDeviceLocation deviceLocation);

    void onTodayPressed();

    void onTomorrowPressed();

    void onFiveDaysPressed();
}
