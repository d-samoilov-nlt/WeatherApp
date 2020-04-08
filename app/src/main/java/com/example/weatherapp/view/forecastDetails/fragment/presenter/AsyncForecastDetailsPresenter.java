package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

import java.util.concurrent.ExecutorService;

public class AsyncForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsPresenter origin;
    private final ExecutorService service;

    public AsyncForecastDetailsPresenter(
            IForecastDetailsPresenter origin, ExecutorService service) {
        this.origin = origin;
        this.service = service;
    }

    @Override
    public void onCreate(ICityLocation cityLocation) {
        service.execute(() -> origin.onCreate(cityLocation));
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
