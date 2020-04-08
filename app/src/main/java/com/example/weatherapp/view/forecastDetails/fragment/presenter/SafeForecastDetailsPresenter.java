package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

public class SafeForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;

    public SafeForecastDetailsPresenter(IForecastDetailsPresenter origin) {
        this.origin = origin;
    }

    @Override
    public void onCreate(ICityLocation cityLocation) {
        try {
            origin.onCreate(cityLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTodayPressed() {
        try {
            origin.onTodayPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTomorrowPressed() {
        try {
            origin.onTomorrowPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFiveDaysPressed() {
        try {
            origin.onFiveDaysPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
