package com.example.weatherapp.view.currenctLocation.presenter;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapi.service.exception.RequestFailedException;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.data.repository.deviceLocation.ILastDeviceLocationRepository;
import com.example.weatherapp.domain.exception.InternetUnreachableException;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.currenctLocation.view.ICurrentLocationView;

public class CurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;
    private final ILastDeviceLocationRepository lastDeviceLocationRepository;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;

    private IDeviceLocation deviceLocation;
    private boolean isCurrentLocationFavorite;

    private ICurrentWeatherResponse currentWeatherResponse;
    private ISeveralDaysWeatherResponse severalDaysWeatherResponse;

    public CurrentLocationPresenter(
            ICurrentLocationView view,
            IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper,
            ILastDeviceLocationRepository lastDeviceLocationRepository,
            IFavoriteLocationRepository favoriteLocationRepository,
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.lastDeviceLocationRepository = lastDeviceLocationRepository;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
    }

    @Override
    public void onCreate() {
        try {
            deviceLocation = lastDeviceLocationRepository.load();

            view.showShortForecastDetails(forecastShortDetailsMapper.map(
                    favoriteLocationRepository.loadByDeviceLocation(deviceLocation).getCurrentWeather()));

            isCurrentLocationFavorite = true;

            view.showForecastDetails(
                    new CityLocation(
                            deviceLocation.getLongitude(),
                            deviceLocation.getLatitude()));

            view.setIsFavoriteSelected(true);
        } catch (NotFoundException ignore) {
            //nop
        }

        if (deviceLocation == null) {
            view.setIsSearchingLocationProcess(true);
        }
        view.startLocationService();
    }

    @Override
    public void onDestroy() {
        view.stopLocationService();
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        if (deviceLocation == null) {
            view.setIsSearchingLocationProcess(false);
        }
        this.deviceLocation = deviceLocation;
        view.stopLocationService();

        lastDeviceLocationRepository.save(deviceLocation);

        view.setIsPermissionRequiredError(false);

        updateForecastDetails();
    }

    private void updateForecastDetails() {
        ICityLocation cityLocation =
                new CityLocation(
                        deviceLocation.getLongitude(),
                        deviceLocation.getLatitude());

        try {
            currentWeatherResponse =
                    getCurrentWeatherByCityLocationUseCase.get(cityLocation);

            view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));
        } catch (InternetUnreachableException | RequestFailedException e) {
            e.printStackTrace();
        } finally {
            view.showForecastDetails(cityLocation);

            view.setIsSearchingLocationProcess(false);
        }
    }

    @Override
    public void onTrySearchAgainPressed() {
        view.setIsPermissionRequiredError(false);
        view.startLocationService();
        view.setIsSearchingLocationProcess(true);
    }

    @Override
    public void onAddToFavoritePressed() {
        if (deviceLocation == null) {
            return;
        }
        isCurrentLocationFavorite = !isCurrentLocationFavorite;

        view.setIsFavoriteSelected(isCurrentLocationFavorite);

        ICityLocation cityLocation =
                new CityLocation(
                        deviceLocation.getLongitude(),
                        deviceLocation.getLatitude());

        if (currentWeatherResponse == null) {
            currentWeatherResponse = getCurrentWeatherByCityLocationUseCase.get(cityLocation);
        }
        if (severalDaysWeatherResponse == null) {
            severalDaysWeatherResponse = getSeveralDaysForecastUseCase.get(cityLocation);
        }
        if (isCurrentLocationFavorite) {
            favoriteLocationRepository.save(
                    new FavoriteLocationCacheData(
                            ForecastUnitsType.CELSIUS.getValue(),
                            currentWeatherResponse.getCityName(),
                            currentWeatherResponse,
                            severalDaysWeatherResponse));
        } else {
            favoriteLocationRepository.deleteByCityName(currentWeatherResponse.getCityName());
        }
    }

    @Override
    public void onRefreshPressed() {
        updateForecastDetails();
    }

    @Override
    public void onPermissionGranted() {
        view.setIsPermissionRequiredError(false);
        view.setIsSearchingLocationProcess(true);
    }

    @Override
    public void onPermissionDenied() {
        view.setIsPermissionRequiredError(true);
        view.setIsSearchingLocationProcess(false);
    }
}
