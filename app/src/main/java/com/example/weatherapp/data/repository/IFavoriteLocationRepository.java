package com.example.weatherapp.data.repository;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.domain.exception.NotFoundException;

import java.util.List;

public interface IFavoriteLocationRepository {
    void save(IFavoriteLocationCacheData data);

    List<IFavoriteLocationCacheData> loadAll() throws NotFoundException;

    IFavoriteLocationCacheData loadByCityName(String cityName) throws NotFoundException;

    IFavoriteLocationCacheData loadByDeviceLocation(IDeviceLocation deviceLocation) throws NotFoundException;

    void deleteByCityName(String cityName);
}
