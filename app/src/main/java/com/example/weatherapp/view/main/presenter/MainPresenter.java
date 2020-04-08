package com.example.weatherapp.view.main.presenter;

import com.example.weatherapp.domain.useCase.launchScreen.ILaunchScreenUseCase;
import com.example.weatherapp.view.main.view.IMainView;

public class MainPresenter implements IMainPresenter {
    private final ILaunchScreenUseCase launchSearchCityScreenUseCase;
    private final IMainView view;

    public MainPresenter(
            ILaunchScreenUseCase launchSearchCityScreenUseCase,
            IMainView view) {

        this.launchSearchCityScreenUseCase = launchSearchCityScreenUseCase;
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.showTabs();
    }

    @Override
    public void onSearchByCityPressed() {
        launchSearchCityScreenUseCase.launch();
    }
}
