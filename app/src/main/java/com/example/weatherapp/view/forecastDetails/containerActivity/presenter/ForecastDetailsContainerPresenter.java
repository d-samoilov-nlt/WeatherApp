package com.example.weatherapp.view.forecastDetails.containerActivity.presenter;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.domain.useCase.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.data.model.deviceLocation.DeviceLocation;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.IForecastDetailsContainerView;

public class ForecastDetailsContainerPresenter implements IForecastDetailsContainerPresenter {
    private final String intentCityName;
    private final IForecastDetailsContainerView view;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;

    private IDeviceLocation deviceLocation;

    public ForecastDetailsContainerPresenter(
            String intentCityName,
            IForecastDetailsContainerView view,
            IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper) {
        this.view = view;
        this.intentCityName = intentCityName;
        this.getCurrentWeatherByCityNameUseCase = getCurrentWeatherByCityNameUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
    }

    @Override
    public void onCreate() {
        ICurrentWeatherResponse currentWeatherResponse =
                getCurrentWeatherByCityNameUseCase.get(intentCityName);

        deviceLocation =
                new DeviceLocation(
                        currentWeatherResponse.getCoordinate().getLongitude(),
                        currentWeatherResponse.getCoordinate().getLatitude());

        view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));

        view.showForecastDetails(deviceLocation);
    }

    @Override
    public void onRefreshPressed() {
        view.updateForecastDetails(deviceLocation);
    }
}
