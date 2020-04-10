package com.example.weatherapp.view.forecastDetails.containerActivity.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.IForecastDetailsContainerView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.IShowForecastDetailsByLocationUseCase;

public class ForecastDetailsContainerPresenter implements IForecastDetailsContainerPresenter {
    private final String intentCityName;
    private final IForecastDetailsContainerView view;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;
    private final IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase;

    private ICityLocation cityLocation;

    public ForecastDetailsContainerPresenter(
            String intentCityName,
            IForecastDetailsContainerView view,
            IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper,
            IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase) {

        this.view = view;
        this.intentCityName = intentCityName;
        this.getCurrentWeatherByCityNameUseCase = getCurrentWeatherByCityNameUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.showForecastDetailsByLocationUseCase = showForecastDetailsByLocationUseCase;
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

        showForecastDetailsByLocationUseCase.show(cityLocation);
    }

    @Override
    public void onRefreshPressed() {
        showForecastDetailsByLocationUseCase.show(cityLocation);
    }
}
