package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;
    private final ExecutorService service;

    public AsyncForecastDetailsPresenter(IForecastDetailsPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onTodayPressed() {
        service.execute(origin::onTodayPressed);
    }

    @Override
    public void onTomorrowPressed() {
        service.execute(origin::onTomorrowPressed);
    }

    @Override
    public void onFiveDaysPressed() {
        service.execute(origin::onFiveDaysPressed);
    }
}
