package com.example.weatherapp.view.locationList.model.useCase;

import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.ArrayList;
import java.util.List;

public class FilterFavoriteLocationListByCityNameUseCase implements IFilterFavoriteLocationListByCityNameUseCase {

    @Override
    public List<IFavoriteLocationItemDisplayModel> filter(String cityName, List<IFavoriteLocationItemDisplayModel> origin) {
        List<IFavoriteLocationItemDisplayModel> filteredDisplayModels = new ArrayList<>();

        for (IFavoriteLocationItemDisplayModel dm : origin) {
            if (dm
                    .getShortDetailsDisplayModel()
                    .getCityName()
                    .toLowerCase()
                    .contains(cityName.toLowerCase())) {
                filteredDisplayModels.add(dm);
            }
        }

        return filteredDisplayModels;
    }
}
