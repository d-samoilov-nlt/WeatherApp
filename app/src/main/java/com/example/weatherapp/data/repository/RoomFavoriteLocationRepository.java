package com.example.weatherapp.data.repository;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.room.RoomFavoriteLocationCacheData;
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
        dao.save(new RoomFavoriteLocationCacheData(data));
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
        return null; // TODO : get from dao
    }

    @Override
    public void deleteByCityName(String cityName) {
        dao.deleteByCityName(cityName);
    }
}
