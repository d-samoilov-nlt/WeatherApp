package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

public interface IForecastDetailsPresenter {
    void onCreate(ICityLocation cityLocation);

    void onTodayPressed();

    void onTomorrowPressed();

    void onFiveDaysPressed();
}
