package com.example.weatherapp.forecastDetails.presenter;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.data.deviceLocation.IDeviceLocation;
import com.example.weatherapp.forecastDetails.model.mapper.IDayForecastMapper;
import com.example.weatherapp.forecastDetails.view.IForecastDetailsView;

public class ForecastDetailsPresenter implements IForecastDetailsPresenter {
    private final IForecastDetailsView view;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IDayForecastMapper todayForecastMapper;

    public ForecastDetailsPresenter(
            IForecastDetailsView view,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IDayForecastMapper todayForecastMapper) {

        this.view = view;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.todayForecastMapper = todayForecastMapper;
    }

    @Override
    public void onCreate(IDeviceLocation deviceLocation) {
        ISeveralDaysWeatherResponse severalDaysWeatherResponse =
                getSeveralDaysForecastUseCase.get(new CityLocation(
                        deviceLocation.getLongitude(),
                        deviceLocation.getLatitude()));

        view.showForecastToday(todayForecastMapper.map(severalDaysWeatherResponse));
    }
}
