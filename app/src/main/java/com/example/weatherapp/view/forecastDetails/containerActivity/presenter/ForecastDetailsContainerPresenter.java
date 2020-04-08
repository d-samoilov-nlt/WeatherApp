package com.example.weatherapp.view.forecastDetails.containerActivity.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.IForecastDetailsContainerView;

public class ForecastDetailsContainerPresenter implements IForecastDetailsContainerPresenter {
    private final String intentCityName;
    private final IForecastDetailsContainerView view;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;

    private ICityLocation cityLocation;

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

        cityLocation =
                new CityLocation(
                        currentWeatherResponse.getCoordinate().getLongitude(),
                        currentWeatherResponse.getCoordinate().getLatitude());

        view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));

        view.showForecastDetails(cityLocation);
    }

    @Override
    public void onRefreshPressed() {
        view.updateForecastDetails(cityLocation);
    }
}
