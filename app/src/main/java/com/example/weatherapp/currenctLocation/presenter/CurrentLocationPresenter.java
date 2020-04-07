package com.example.weatherapp.currenctLocation.presenter;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapp.currenctLocation.view.ICurrentLocationView;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.exception.PermissionDeniedException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;

public class CurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;

    public CurrentLocationPresenter(
            ICurrentLocationView view,
            IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
    }

    @Override
    public void onCreate() {
        view.setIsSearchingLocationProcess(true);
    }

    @Override
    public void onStart() {
        view.startLocationService();
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        view.setIsPermissionRequiredError(false);
        ICurrentWeatherResponse currentWeatherResponse;

        try {
            currentWeatherResponse =
                    getCurrentWeatherByCityLocationUseCase.get(
                            new CityLocation(
                                    deviceLocation.getLongitude(),
                                    deviceLocation.getLatitude()));

            view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));
            view.showForecastDetails(deviceLocation);

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
