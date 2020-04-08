package com.example.weatherapp.view.main.presenter;

import java.util.concurrent.ExecutorService;

public class AsyncMainPresenter implements IMainPresenter {
    private final IMainPresenter origin;
    private final ExecutorService service;

    public AsyncMainPresenter(IMainPresenter origin, ExecutorService service) {
        this.origin = origin;
        this.service = service;
    }

    @Override
    public void onCreate() {
        service.execute(origin::onCreate);
    }

    @Override
    public void onSearchByCityPressed() {
        service.execute(origin::onSearchByCityPressed);
    }
}
