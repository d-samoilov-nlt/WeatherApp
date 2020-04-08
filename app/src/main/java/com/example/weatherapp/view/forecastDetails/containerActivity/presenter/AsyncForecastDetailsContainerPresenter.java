package com.example.weatherapp.view.forecastDetails.containerActivity.presenter;

import java.util.concurrent.ExecutorService;

public class AsyncForecastDetailsContainerPresenter implements IForecastDetailsContainerPresenter {
    private final IForecastDetailsContainerPresenter origin;
    private final ExecutorService service;

    public AsyncForecastDetailsContainerPresenter(
            IForecastDetailsContainerPresenter origin,
            ExecutorService service) {
        this.origin = origin;
        this.service = service;
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
