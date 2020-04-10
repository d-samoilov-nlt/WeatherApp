package com.example.weatherapp.view.locationList.presenter;

import com.example.weatherapp.provider.ExecutorServiceProvider;
import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.concurrent.ExecutorService;

public class AsyncFavoriteLocationListPresenter implements IFavoriteLocationListPresenter {
    private final IFavoriteLocationListPresenter origin;
    private final ExecutorService service;

    public AsyncFavoriteLocationListPresenter(IFavoriteLocationListPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
    }

    @Override
    public void onStart() {
        service.execute(origin::onStart);
    }

    @Override
    public void onSearchIconPressed() {
        service.execute(origin::onSearchIconPressed);
    }

    @Override
    public void onSearchDataEntered(String data) {
        service.execute(() -> origin.onSearchDataEntered(data));
    }

    @Override
    public void onLocationItemPressed(IFavoriteLocationItemDisplayModel dm) {
        service.execute(() -> origin.onLocationItemPressed(dm));
    }
}
