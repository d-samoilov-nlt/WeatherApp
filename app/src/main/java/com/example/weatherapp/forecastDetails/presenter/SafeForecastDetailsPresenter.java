package com.example.weatherapp.forecastDetails.presenter;

import com.example.weatherapp.data.deviceLocation.IDeviceLocation;

public class SafeForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;

    public SafeForecastDetailsPresenter(IForecastDetailsPresenter origin) {
        this.origin = origin;
    }

    @Override
    public void onCreate(IDeviceLocation deviceLocation) {
        try {
            origin.onCreate(deviceLocation);
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
