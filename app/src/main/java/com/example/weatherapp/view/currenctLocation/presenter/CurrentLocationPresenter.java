package com.example.weatherapp.view.currenctLocation.presenter;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.data.repository.deviceLocation.ILastDeviceLocationRepository;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.example.weatherapp.domain.mapper.IForecastShortDetailsMapper;
import com.example.weatherapp.view.currenctLocation.view.ICurrentLocationView;

public class CurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationView view;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;
    private final IForecastShortDetailsMapper forecastShortDetailsMapper;
    private final ILastDeviceLocationRepository lastDeviceLocationRepository;
    private final IFavoriteLocationRepository favoriteLocationRepository;

    private IDeviceLocation deviceLocation;
    private boolean isCurrentLocationFavorite;

    public CurrentLocationPresenter(
            ICurrentLocationView view,
            IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase,
            IForecastShortDetailsMapper forecastShortDetailsMapper,
            ILastDeviceLocationRepository lastDeviceLocationRepository,
            IFavoriteLocationRepository favoriteLocationRepository) {

        this.view = view;
        this.getCurrentWeatherByCityLocationUseCase = getCurrentWeatherByCityLocationUseCase;
        this.forecastShortDetailsMapper = forecastShortDetailsMapper;
        this.lastDeviceLocationRepository = lastDeviceLocationRepository;
        this.favoriteLocationRepository = favoriteLocationRepository;
    }

    @Override
    public void onCreate() {
        view.setIsSearchingLocationProcess(true);
        try {
            deviceLocation = lastDeviceLocationRepository.load();
            updateForecastDetails();
        } catch (NotFoundException ignore) {
            //nop
        }
    }

    @Override
    public void onStart() {
        view.startLocationService();
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        view.setIsPermissionRequiredError(false);
        updateForecastDetails();
    }

    private void updateForecastDetails() {
        ICityLocation cityLocation =
                new CityLocation(
                        deviceLocation.getLongitude(),
                        deviceLocation.getLatitude());

        ICurrentWeatherResponse currentWeatherResponse =
                getCurrentWeatherByCityLocationUseCase.get(cityLocation);

        view.showShortForecastDetails(forecastShortDetailsMapper.map(currentWeatherResponse));
        view.showForecastDetails(cityLocation);

        lastDeviceLocationRepository.save(deviceLocation);
    }

    @Override
    public void onTrySearchAgainPressed() {
        view.setIsPermissionRequiredError(false);
        view.startLocationService();
        view.setIsSearchingLocationProcess(true);
    }

    @Override
    public void onAddToFavoritePressed() {
        isCurrentLocationFavorite = !isCurrentLocationFavorite;
        // TODO : add to favorite
    }

    @Override
    public void onRefreshPressed() {
        updateForecastDetails();
    }
}
