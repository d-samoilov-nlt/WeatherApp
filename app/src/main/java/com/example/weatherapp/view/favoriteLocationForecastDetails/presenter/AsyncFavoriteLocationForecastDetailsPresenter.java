package com.example.weatherapp.view.favoriteLocationForecastDetails.presenter;

import java.util.concurrent.ExecutorService;

public class AsyncFavoriteLocationForecastDetailsPresenter implements IFavoriteLocationForecastDetailsPresenter {
    private final IFavoriteLocationForecastDetailsPresenter origin;
    private final ExecutorService service;

    public AsyncFavoriteLocationForecastDetailsPresenter(
            IFavoriteLocationForecastDetailsPresenter origin,
            ExecutorService service) {
        this.origin = origin;
        this.service = service;
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
