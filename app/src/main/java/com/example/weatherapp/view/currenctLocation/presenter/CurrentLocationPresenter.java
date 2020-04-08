package com.example.weatherapp.view.currenctLocation.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.exception.PermissionDeniedException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.currenctLocation.view.ICurrentLocationView;

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

        ICityLocation cityLocation =
                new CityLocation(
                        deviceLocation.getLongitude(),
                        deviceLocation.getLatitude());

        ICurrentWeatherResponse currentWeatherResponse;

        try {
            currentWeatherResponse =
                    getCurrentWeatherByCityLocationUseCase.get(cityLocation);

            view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));
            view.showForecastDetails(cityLocation);

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
