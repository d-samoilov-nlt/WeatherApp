package com.example.weatherapp.view.locationList.view;

import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public interface IFavoriteLocationListView {
    void showSearchField();

    void updateFavoriteLocationsList(List<IFavoriteLocationItemDisplayModel> models);

    void setEmptyListMessageVisibility(boolean isVisible);
}
