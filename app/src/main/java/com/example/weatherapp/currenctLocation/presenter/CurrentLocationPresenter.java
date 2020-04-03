package com.example.weatherapp.currenctLocation.presenter;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapp.currenctLocation.view.ICurrentLocationView;
import com.example.weatherapp.data.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.exception.PermissionDeniedException;

public class CurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;

    public CurrentLocationPresenter(
            ICurrentLocationView view,
            IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
    }

    @Override
    public void onCreate() {
        view.startLocationService();
        view.setIsSearchingLocationProcess(true);
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        view.setIsPermissionRequiredError(false);

        try {
            ICurrentWeatherResponse currentWeatherResponse =
                    getCurrentWeatherByCityLocationUseCase.get(
                            new CityLocation(
                                    deviceLocation.getLongitude(),
                                    deviceLocation.getLatitude()));


        } catch (PermissionDeniedException e) {
            view.setIsPermissionRequiredError(true);
        } finally {
            view.setIsSearchingLocationProcess(false);
        }
    }

    @Override
    public void onTrySearchAgainPressed() {
        view.setIsPermissionRequiredError(false);
        view.startLocationService();
        view.setIsSearchingLocationProcess(true);
    }
}
