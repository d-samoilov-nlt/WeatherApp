package com.example.weatherapp.locationList.model.useCase;

import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public interface IFilterFavoriteLocationListByCityNameUseCase {
    List<IFavoriteLocationItemDisplayModel> filter(String cityName, List<IFavoriteLocationItemDisplayModel> origin);
}
