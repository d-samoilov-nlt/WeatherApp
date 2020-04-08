package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.IDayForecastMapper;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.ISeveralDaysForecastMapper;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.exception.DamagedForecastResponseException;
import com.example.weatherapp.view.forecastDetails.fragment.view.IForecastDetailsView;

public class ForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsView view;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IDayForecastMapper todayForecastMapper;
    private final IDayForecastMapper tomorrowForecastMapper;
    private final ISeveralDaysForecastMapper severalDaysForecastMapper;
    private final ICityLocation cityLocation;
    private final int forecastUnitType;

    private ISeveralDaysWeatherResponse severalDaysWeatherResponse;

    public ForecastDetailsPresenter(
            IForecastDetailsView view,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IDayForecastMapper todayForecastMapper,
            IDayForecastMapper tomorrowForecastMapper,
            ISeveralDaysForecastMapper severalDaysForecastMapper,
            ICityLocation cityLocation,
            int forecastUnitType) {

        this.view = view;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.todayForecastMapper = todayForecastMapper;
        this.tomorrowForecastMapper = tomorrowForecastMapper;
        this.severalDaysForecastMapper = severalDaysForecastMapper;
        this.cityLocation = cityLocation;
        this.forecastUnitType = forecastUnitType;
    }

    @Override
    public void onCreate() {
        onTodayPressed();
    }

    @Override
    public void onTodayPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        try {
            if (severalDaysWeatherResponse == null) {
                severalDaysWeatherResponse =
                        getSeveralDaysForecastUseCase.get(cityLocation, forecastUnitType);
            }
            view.showForecastToday(todayForecastMapper.map(severalDaysWeatherResponse));
            view.showDetailsLoadingProgress(false);
        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        }
    }

    @Override
    public void onTomorrowPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        try {

            if (severalDaysWeatherResponse == null) {
                severalDaysWeatherResponse =
                        getSeveralDaysForecastUseCase.get(cityLocation, forecastUnitType);
            }
            view.showForecastTomorrow(tomorrowForecastMapper.map(severalDaysWeatherResponse));
            view.showDetailsLoadingProgress(false);
        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        }
    }

    @Override
    public void onFiveDaysPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        try {
            if (severalDaysWeatherResponse == null) {
                severalDaysWeatherResponse =
                        getSeveralDaysForecastUseCase.get(cityLocation, forecastUnitType);
            }

            view.showForecastForSeveralDays(severalDaysForecastMapper.map(severalDaysWeatherResponse));
            view.showDetailsLoadingProgress(false);
        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        }
    }
}
