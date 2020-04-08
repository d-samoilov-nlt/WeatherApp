package com.example.weatherapp.view.locationList.model.useCase;

import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public interface IFilterFavoriteLocationListByCityNameUseCase {
    List<IFavoriteLocationItemDisplayModel> filter(String cityName, List<IFavoriteLocationItemDisplayModel> origin);
}
