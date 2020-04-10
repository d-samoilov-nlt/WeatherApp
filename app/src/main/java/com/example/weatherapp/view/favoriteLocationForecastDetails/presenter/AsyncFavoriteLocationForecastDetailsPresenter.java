package com.example.weatherapp.view.favoriteLocationForecastDetails.presenter;

import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncFavoriteLocationForecastDetailsPresenter implements IFavoriteLocationForecastDetailsPresenter {
    private final IFavoriteLocationForecastDetailsPresenter origin;
    private final ExecutorService service;

    public AsyncFavoriteLocationForecastDetailsPresenter(IFavoriteLocationForecastDetailsPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onFavoriteBtnPressed() {
        service.execute(origin::onFavoriteBtnPressed);
    }

    @Override
    public void onRefreshPressed() {
        service.execute(origin::onRefreshPressed);
    }
}
