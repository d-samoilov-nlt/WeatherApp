package com.example.weatherapp.dataRoom.repository;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.dataRoom.model.forecast.currentWeather.RoomCurrentWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.relation.RoomFavoriteLocationWithWeather;
import com.example.weatherapp.dataRoom.model.forecast.severalDaysWeather.RoomSeveralDaysWeatherResponse;
import com.example.weatherapp.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class RoomFavoriteLocationRepository implements IFavoriteLocationRepository {
    private final IFavoriteLocationDao dao;

    public RoomFavoriteLocationRepository(IFavoriteLocationDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(IFavoriteLocationCacheData data) {
        dao.save(
                new RoomFavoriteLocationWithWeather(
                        data.getCityName(),
                        data.getForecastUnitType(),
                        new RoomCurrentWeatherResponse(data.getCurrentWeather()),
                        new RoomSeveralDaysWeatherResponse(data.getSeveralDaysForecast())
                ));
    }

    @Override
    public List<IFavoriteLocationCacheData> loadAll() throws NotFoundException {
        try {
            return new ArrayList<>(dao.loadAll());
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public IFavoriteLocationCacheData loadByCityName(String cityName) throws NotFoundException {
        try {
            return dao.loadByCityName(cityName);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public IFavoriteLocationCacheData loadByDeviceLocation(IDeviceLocation deviceLocation) throws NotFoundException {
        IFavoriteLocationCacheData cacheData =
                dao.loadByCityLocation(
                        (int) deviceLocation.getLatitude(),
                        (int) deviceLocation.getLongitude());
        if (cacheData == null) {
            throw new NotFoundException();
        }

        return cacheData;
    }

    @Override
    public void deleteByCityName(String cityName) {
        dao.deleteByCityName(cityName);
    }
}
