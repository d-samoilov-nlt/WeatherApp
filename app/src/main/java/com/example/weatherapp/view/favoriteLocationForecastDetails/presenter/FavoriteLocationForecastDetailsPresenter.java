package com.example.weatherapp.view.favoriteLocationForecastDetails.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapi.service.exception.RequestFailedException;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.exception.InternetUnreachableException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.IFavoriteLocationForecastDetailsView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.IShowForecastDetailsByLocationUseCase;

public class FavoriteLocationForecastDetailsPresenter implements IFavoriteLocationForecastDetailsPresenter {
    private final IFavoriteLocationForecastDetailsView view;
    private final String cityName;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;
    private final IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase;

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
            IForecastShortDetailsMapper forecastShortDetailsMapper,
            IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase) {

        this.view = view;
        this.cityName = cityName;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.getCurrentWeatherByCityNameUseCase = getCurrentWeatherByCityNameUseCase;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.showForecastDetailsByLocationUseCase = showForecastDetailsByLocationUseCase;
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

        showForecastDetailsByLocationUseCase.show(cityLocation, unitType);
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

        try {
            IFavoriteLocationCacheData
                    updatedLocationCacheData =
                    new FavoriteLocationCacheData(
                            locationCacheData.getForecastUnitType(),
                            locationCacheData.getCityName(),
                            getCurrentWeatherByCityNameUseCase.get(cityName, unitType),
                            getSeveralDaysForecastUseCase.get(cityLocation, unitType));

            locationCacheData = updatedLocationCacheData;

            showForecastDetailsByLocationUseCase.show(cityLocation, unitType);

            view.showShortForecastDetails(
                    forecastShortDetailsMapper.map(
                            locationCacheData.getCurrentWeather(),
                            unitType));
            if (isFavoriteSelected) {
                favoriteLocationRepository.save(updatedLocationCacheData);
            }
        } catch (RequestFailedException | InternetUnreachableException e) {
            e.printStackTrace();
        } finally {
            view.setLoadingProcess(false);
        }
    }
}
