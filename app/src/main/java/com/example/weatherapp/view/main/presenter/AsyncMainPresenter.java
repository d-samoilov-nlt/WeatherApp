package com.example.weatherapp.view.main.presenter;

import com.example.weatherapp.provider.ExecutorServiceProvider;

import java.util.concurrent.ExecutorService;

public class AsyncMainPresenter implements IMainPresenter {
    private final IMainPresenter origin;
    private final ExecutorService service;

    public AsyncMainPresenter(IMainPresenter origin) {
        this.origin = origin;
        this.service = ExecutorServiceProvider.get();
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
