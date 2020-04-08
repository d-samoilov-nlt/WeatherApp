package com.example.weatherapp.locationList.view;

import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public interface IFavoriteLocationListView {
    void showSearchField();

    void updateFavoriteLocationsList(List<IFavoriteLocationItemDisplayModel> models);

    void setEmptyListMessageVisibility(boolean isVisible);
}
