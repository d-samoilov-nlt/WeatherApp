package com.example.weatherapp.view.main.presenter;

import com.example.weatherapp.domain.useCase.launchScreen.ILaunchScreenUseCase;
import com.example.weatherapp.domain.useCase.updateFavoriteLocation.IEnqueueUpdatingFavoriteLocationWorkerUseCase;
import com.example.weatherapp.view.main.view.IMainView;

public class MainPresenter implements IMainPresenter {
    private final ILaunchScreenUseCase launchSearchCityScreenUseCase;
    private final IMainView view;
    private final IEnqueueUpdatingFavoriteLocationWorkerUseCase enqueueUpdatingFavoriteLocationWorkerUseCase;

    public MainPresenter(
            ILaunchScreenUseCase launchSearchCityScreenUseCase,
            IMainView view,
            IEnqueueUpdatingFavoriteLocationWorkerUseCase enqueueUpdatingFavoriteLocationWorkerUseCase) {

        this.launchSearchCityScreenUseCase = launchSearchCityScreenUseCase;
        this.view = view;
        this.enqueueUpdatingFavoriteLocationWorkerUseCase = enqueueUpdatingFavoriteLocationWorkerUseCase;
    }

    @Override
    public void onCreate() {
        enqueueUpdatingFavoriteLocationWorkerUseCase.enqueue();
        view.showTabs();
    }

    @Override
    public void onSearchByCityPressed() {
        launchSearchCityScreenUseCase.launch();
    }
}
