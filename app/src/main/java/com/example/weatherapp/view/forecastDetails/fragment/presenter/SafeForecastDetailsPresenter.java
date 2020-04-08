package com.example.weatherapp.view.forecastDetails.fragment.presenter;

public class SafeForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;

    public SafeForecastDetailsPresenter(IForecastDetailsPresenter origin) {
        this.origin = origin;
    }

    @Override
    public void onCreate() {
        try {
            origin.onCreate();
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
