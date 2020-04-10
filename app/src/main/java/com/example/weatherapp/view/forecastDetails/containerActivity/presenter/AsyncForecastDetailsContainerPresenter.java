package com.example.weatherapp.view.forecastDetails.containerActivity.presenter;

import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncForecastDetailsContainerPresenter implements IForecastDetailsContainerPresenter {
    private final IForecastDetailsContainerPresenter origin;
    private final ExecutorService service;

    public AsyncForecastDetailsContainerPresenter(IForecastDetailsContainerPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onRefreshPressed() {
        service.execute(origin::onRefreshPressed);
    }
}
