package com.example.weatherapp.view.favoriteLocationForecastDetails.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.IFavoriteLocationForecastDetailsView;

public class FavoriteLocationForecastDetailsPresenter implements IFavoriteLocationForecastDetailsPresenter {
    private final IFavoriteLocationForecastDetailsView view;
    private final String cityName;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;

    private boolean isFavoriteSelected;
    private ICityLocation cityLocation;
    private int unitType;

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
        unitType = locationCacheData.getForecastUnitType();
        cityLocation =
                new CityLocation(
                        locationCacheData.getCurrentWeather().getCoordinate().getLongitude(),
                        locationCacheData.getCurrentWeather().getCoordinate().getLatitude());

        view.showForecastDetails(cityLocation);
        view.setIsFavoriteSelected(isFavoriteSelected);
        view.showShortForecastDetails(
                forecastShortDetailsMapper.map(
                        locationCacheData.getCurrentWeather(),
                        locationCacheData.getForecastUnitType()));
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
                        locationCacheData.getForecastUnitType(),
                        locationCacheData.getCityName(),
                        getCurrentWeatherByCityNameUseCase.get(cityName, unitType),
                        getSeveralDaysForecastUseCase.get(cityLocation, unitType));

        locationCacheData = updatedLocationCacheData;
        favoriteLocationRepository.save(updatedLocationCacheData);

        view.showForecastDetails(cityLocation);
        view.showShortForecastDetails(
                forecastShortDetailsMapper.map(
                        locationCacheData.getCurrentWeather(),
                        unitType));
        view.setLoadingProcess(false);
    }
}
