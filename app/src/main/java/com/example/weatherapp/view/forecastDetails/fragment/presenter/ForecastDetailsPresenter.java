package com.example.weatherapp.view.forecastDetails.fragment.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapi.service.exception.RequestFailedException;
import com.example.weatherapp.data.model.deviceLocation.DeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.exception.NotFoundException;
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
    private final IFavoriteLocationRepository favoriteLocationRepository;

    private ISeveralDaysWeatherResponse severalDaysWeatherResponse;
    private IFavoriteLocationCacheData currentLocationCachedData;

    public ForecastDetailsPresenter(
            IForecastDetailsView view,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IDayForecastMapper todayForecastMapper,
            IDayForecastMapper tomorrowForecastMapper,
            ISeveralDaysForecastMapper severalDaysForecastMapper,
            ICityLocation cityLocation,
            int forecastUnitType,
            IFavoriteLocationRepository favoriteLocationRepository) {

        this.view = view;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.todayForecastMapper = todayForecastMapper;
        this.tomorrowForecastMapper = tomorrowForecastMapper;
        this.severalDaysForecastMapper = severalDaysForecastMapper;
        this.cityLocation = cityLocation;
        this.forecastUnitType = forecastUnitType;
        this.favoriteLocationRepository = favoriteLocationRepository;
    }

    @Override
    public void onCreate() {
        view.showDetailsLoadingProgress(true);

        try {
            currentLocationCachedData =
                    favoriteLocationRepository.loadByDeviceLocation(
                            new DeviceLocation(
                                    cityLocation.getLongitude(),
                                    cityLocation.getLatitude()));

            severalDaysWeatherResponse = currentLocationCachedData.getSeveralDaysForecast();
            updateSeveralDaysWeatherResponse();

            showTodayForecast();
        } catch (NotFoundException ignore) {
            severalDaysWeatherResponse = getSeveralDaysForecastUseCase.get(cityLocation, forecastUnitType);

            showTodayForecast();
        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        } catch (RequestFailedException e) {
            e.printStackTrace();

            if (severalDaysWeatherResponse != null) {
                showTodayForecast();
            } else {
                view.showLoadingError(true);
            }
        }
    }

    @Override
    public void onTodayPressed() {
        showTodayForecast();
    }

    private void showTodayForecast() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        view.showForecastToday(todayForecastMapper.map(severalDaysWeatherResponse));

        view.showDetailsLoadingProgress(false);
    }

    @Override
    public void onTomorrowPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        view.showForecastTomorrow(tomorrowForecastMapper.map(severalDaysWeatherResponse));

        view.showDetailsLoadingProgress(false);
    }

    @Override
    public void onFiveDaysPressed() {
        view.showLoadingError(false);
        view.showDetailsLoadingProgress(true);

        try {
            view.showForecastForSeveralDays(severalDaysForecastMapper.map(severalDaysWeatherResponse));
            view.showDetailsLoadingProgress(false);

        } catch (DamagedForecastResponseException e) {
            e.printStackTrace();
            view.showLoadingError(true);
        }
    }

    private void updateSeveralDaysWeatherResponse() {
        severalDaysWeatherResponse = getSeveralDaysForecastUseCase.get(cityLocation, forecastUnitType);

        if (currentLocationCachedData != null) {
            IFavoriteLocationCacheData updatedCacheData =
                    new FavoriteLocationCacheData(
                            forecastUnitType,
                            currentLocationCachedData.getCityName(),
                            currentLocationCachedData.getCurrentWeather(),
                            severalDaysWeatherResponse);
            favoriteLocationRepository.save(updatedCacheData);
        }
    }
}
