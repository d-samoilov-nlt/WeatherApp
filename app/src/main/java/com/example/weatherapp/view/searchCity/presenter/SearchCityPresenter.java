package com.example.weatherapp.view.searchCity.presenter;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.IGetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapi.service.exception.RequestFailedException;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.exception.InternetUnreachableException;
import com.example.weatherapp.service.ILocationService;
import com.example.weatherapp.view.searchCity.model.ILaunchForecastDetailsScreenUseCase;
import com.example.weatherapp.view.searchCity.view.ISearchCityView;

public class SearchCityPresenter implements ISearchCityPresenter {
    private final ISearchCityView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;
    private final IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final ILaunchForecastDetailsScreenUseCase launchFavoriteLocationForecastDetailsUseCase;
    private final ILaunchForecastDetailsScreenUseCase launchForecastDetailsUseCase;
    private final ILocationService locationService;

    private boolean isLocationServiceEnabled = false;
    private boolean isFavoriteSelected = false;
    private int locationUnitType;

    private ICurrentWeatherResponse currentWeatherResponse;

    public SearchCityPresenter(
            ISearchCityView view,
            IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase,
            IGetCurrentWeatherByCityNameUseCase getCurrentWeatherByCityNameUseCase,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            IFavoriteLocationRepository favoriteLocationRepository,
            ILaunchForecastDetailsScreenUseCase launchFavoriteLocationForecastDetailsUseCase,
            ILaunchForecastDetailsScreenUseCase launchForecastDetailsUseCase,
            ILocationService locationService) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
        this.getCurrentWeatherByCityNameUseCase = getCurrentWeatherByCityNameUseCase;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.launchFavoriteLocationForecastDetailsUseCase = launchFavoriteLocationForecastDetailsUseCase;
        this.launchForecastDetailsUseCase = launchForecastDetailsUseCase;
        this.locationService = locationService;

        locationUnitType = ForecastUnitsType.CELSIUS.getValue();
    }

    @Override
    public void onDestroy() {
        if (isLocationServiceEnabled) {
            locationService.stopService();
        }
    }

    @Override
    public void onLocationIconPressed(boolean isLocationEnabled) {
        isLocationServiceEnabled = isLocationEnabled;

        if (isLocationServiceEnabled) {
            locationService.startService();
        } else {
            locationService.stopService();
        }
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        try {
            this.currentWeatherResponse =
                    getCurrentWeatherByCityLocationUseCase.get(
                            new CityLocation(
                                    deviceLocation.getLongitude(),
                                    deviceLocation.getLatitude()));

            view.setDeviceLocationInfo(currentWeatherResponse.getCityName());
            view.setViewWeatherBtnEnabled(true);
        } catch (RequestFailedException e) {
            view.showCityNotFoundError(true);
        } finally {
            isLocationServiceEnabled = false;
            locationService.stopService();
            view.setLocationIconEnabled(false);
        }
    }

    @Override
    public void onLocationEntered(String location) {
        view.setViewWeatherBtnEnabled(false);

        if (location.trim().isEmpty()) {
            return;
        }

        view.showCityNotFoundError(false);

        try {
            this.currentWeatherResponse =
                    getCurrentWeatherByCityNameUseCase.get(location);

            view.setViewWeatherBtnEnabled(true);
        } catch (InternetUnreachableException | RequestFailedException e) {
            view.showCityNotFoundError(true);
        }
    }

    @Override
    public void onViewWeatherPressed() {
        locationService.stopService();
        if (isFavoriteSelected) {

            favoriteLocationRepository.save(
                    new FavoriteLocationCacheData(
                            locationUnitType,
                            currentWeatherResponse.getCityName(),
                            getCurrentWeatherByCityNameUseCase.get(currentWeatherResponse.getCityName(),
                                    locationUnitType),
                            getSeveralDaysForecastUseCase.get(
                                    new CityLocation(
                                            currentWeatherResponse.getCoordinate().getLongitude(),
                                            currentWeatherResponse.getCoordinate().getLatitude()),
                                    locationUnitType)));

            launchFavoriteLocationForecastDetailsUseCase.launch(currentWeatherResponse.getCityName());
        } else {
            launchForecastDetailsUseCase.launch(currentWeatherResponse.getCityName());
        }
    }

    @Override
    public void onFavoriteSelected(boolean isSelected) {
        isFavoriteSelected = isSelected;
    }

    @Override
    public void onUnitTypeSelected(boolean isSelected) {
        locationUnitType =
                isSelected ?
                        ForecastUnitsType.FAHRENHEIT.getValue()
                        : ForecastUnitsType.CELSIUS.getValue();
    }
}
