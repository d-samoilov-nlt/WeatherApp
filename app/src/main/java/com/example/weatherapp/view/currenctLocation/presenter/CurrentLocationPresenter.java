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
import com.example.weatherapp.data.repository.ILastDeviceLocationRepository;
import com.example.weatherapp.domain.exception.InternetUnreachableException;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.service.ILocationService;
import com.example.weatherapp.view.currenctLocation.view.ICurrentLocationView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.IShowForecastDetailsByLocationUseCase;

public class CurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;
    private final ILastDeviceLocationRepository lastDeviceLocationRepository;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final ILocationService locationService;
    private final IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase;

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
            IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase,
            ILocationService locationService,
            IShowForecastDetailsByLocationUseCase showForecastDetailsByLocationUseCase) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.lastDeviceLocationRepository = lastDeviceLocationRepository;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.getSeveralDaysForecastUseCase = getSeveralDaysForecastUseCase;
        this.locationService = locationService;
        this.showForecastDetailsByLocationUseCase = showForecastDetailsByLocationUseCase;
    }

    @Override
    public void onCreate() {
        try {
            deviceLocation = lastDeviceLocationRepository.load();

            view.showShortForecastDetails(forecastShortDetailsMapper.map(
                    favoriteLocationRepository.loadByDeviceLocation(deviceLocation).getCurrentWeather()));

            isCurrentLocationFavorite = true;

            showForecastDetailsByLocationUseCase
                    .show(new CityLocation(
                            deviceLocation.getLongitude(),
                            deviceLocation.getLatitude()));

            view.setIsFavoriteSelected(true);
        } catch (NotFoundException ignore) {
            //nop
        }

        if (deviceLocation == null) {
            view.setIsSearchingLocationProcess(true);
        }
        locationService.startService();
    }

    @Override
    public void onDestroy() {
        locationService.stopService();
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        if (deviceLocation == null) {
            view.setIsSearchingLocationProcess(false);
        }
        this.deviceLocation = deviceLocation;
        locationService.stopService();

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
            showForecastDetailsByLocationUseCase.show(cityLocation);

            view.setIsSearchingLocationProcess(false);
        }
    }

    @Override
    public void onTrySearchAgainPressed() {
        view.setIsPermissionRequiredError(false);
        locationService.startService();
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
    }

    @Override
    public void onPermissionDenied() {
        view.setIsPermissionRequiredError(true);
    }
}
