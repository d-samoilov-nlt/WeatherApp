package com.example.weatherapp.favoriteLocationForecastDetails.presenter;

import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.data.model.deviceLocation.DeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.favoriteLocationForecastDetails.view.IFavoriteLocationForecastDetailsView;

public class FavoriteLocationForecastDetailsPresenter implements IFavoriteLocationForecastDetailsPresenter {
    private final IFavoriteLocationForecastDetailsView view;
    private final String cityName;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;

    private boolean isFavoriteSelected;

    private IFavoriteLocationCacheData locationCacheData;

    public FavoriteLocationForecastDetailsPresenter(
            IFavoriteLocationForecastDetailsView view,
            String cityName,
            IFavoriteLocationRepository favoriteLocationRepository,
            IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper) {

        this.view = view;
        this.cityName = cityName;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.getCurrentWeatherByCityNameUseCase = getCurrentWeatherByCityNameUseCase;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.isFavoriteSelected = true;
    }

    @Override
    public void onCreate() {
        locationCacheData = favoriteLocationRepository.loadByCityName(cityName);
        view.showForecastDetails(
                new DeviceLocation(
                        locationCacheData.getCurrentWeather().getCoordinate().getLongitude(),
                        locationCacheData.getCurrentWeather().getCoordinate().getLatitude()
                ));
        view.setIsFavoriteSelected(isFavoriteSelected);
        view.showShortForecastDetails(
                forecastShortDetailsMapper.map(
                        locationCacheData.getCurrentWeather()));
    }

    @Override
    public void onFavoriteBtnPressed() {
        isFavoriteSelected = !isFavoriteSelected;
        if (isFavoriteSelected) {
            favoriteLocationRepository.save(locationCacheData);
        } else {
            favoriteLocationRepository.deleteByCityName(cityName);
        }
        view.setIsFavoriteSelected(isFavoriteSelected);
    }

    @Override
    public void onRefreshPressed() {
        view.setLoadingProcess(true);

        IFavoriteLocationCacheData
                updatedLocationCacheData =
                new FavoriteLocationCacheData(
                        locationCacheData.getUtilType(),
                        locationCacheData.getCityName(),
                        getCurrentWeatherByCityNameUseCase.get(cityName),
                        getSeveralDaysForecastUseCase.get(
                                new CityLocation(
                                        locationCacheData.getCurrentWeather().getCoordinate().getLongitude(),
                                        locationCacheData.getCurrentWeather().getCoordinate().getLatitude()
                                )));

        locationCacheData = updatedLocationCacheData;
        favoriteLocationRepository.save(updatedLocationCacheData);

        view.showForecastDetails(
                new DeviceLocation(
                        locationCacheData.getCurrentWeather().getCoordinate().getLongitude(),
                        locationCacheData.getCurrentWeather().getCoordinate().getLatitude()
                ));

        view.showShortForecastDetails(
                forecastShortDetailsMapper.map(
                        locationCacheData.getCurrentWeather()));
        view.setLoadingProcess(false);
    }
}
