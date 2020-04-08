package com.example.weatherapp.locationList.model.mapper;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public interface IFavoriteLocationItemMapper {
    List<IFavoriteLocationItemDisplayModel> map(List<IFavoriteLocationCacheData> cacheData);
}
