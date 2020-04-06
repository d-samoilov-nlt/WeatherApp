package com.example.weatherapp.forecastDetails.presenter;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.data.deviceLocation.IDeviceLocation;
import com.example.weatherapp.forecastDetails.model.mapper.IDayForecastMapper;
import com.example.weatherapp.forecastDetails.model.mapper.ISeveralDaysForecastMapper;
import com.example.weatherapp.forecastDetails.model.mapper.exception.DamagedForecastResponseException;
import com.example.weatherapp.forecastDetails.view.IForecastDetailsView;

public class ForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsView view;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IDayForecastMapper todayForecastMapper;
    private final IDayForecastMapper tomorrowForecastMapper;
    private final ISeveralDaysForecastMapper severalDaysForecastMapper;

    private ISeveralDaysWeatherResponse severalDaysWeatherResponse;
    private IDeviceLocation deviceLocation;

    public ForecastDetailsPresenter(
            IForecastDetailsView view,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IDayForecastMapper todayForecastMapper,
            IDayForecastMapper tomorrowForecastMapper,
            ISeveralDaysForecastMapper severalDaysForecastMapper) {

        this.view = view;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.todayForecastMapper = todayForecastMapper;
        this.tomorrowForecastMapper = tomorrowForecastMapper;
        this.severalDaysForecastMapper = severalDaysForecastMapper;
    }

    @Override
    public void onCreate(IDeviceLocation deviceLocation) {
        this.deviceLocation = deviceLocation;
        this.onTodayPressed();
    }

    @Override
    public void onTodayPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        try {
            if (severalDaysWeatherResponse == null) {
                severalDaysWeatherResponse =
                        getSeveralDaysForecastUseCase.get(new CityLocation(
                                deviceLocation.getLongitude(),
                                deviceLocation.getLatitude()));
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
                        getSeveralDaysForecastUseCase.get(new CityLocation(
                                deviceLocation.getLongitude(),
                                deviceLocation.getLatitude()));
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
                        getSeveralDaysForecastUseCase.get(new CityLocation(
                                deviceLocation.getLongitude(),
                                deviceLocation.getLatitude()));
            }

            view.showForecastForSeveralDays(severalDaysForecastMapper.map(severalDaysWeatherResponse));
            view.showDetailsLoadingProgress(false);
        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        }
    }
}
