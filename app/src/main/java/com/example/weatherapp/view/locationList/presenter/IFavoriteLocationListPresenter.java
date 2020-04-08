package com.example.weatherapp.view.locationList.presenter;

import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

public interface IFavoriteLocationListPresenter {
    void onStart();

    void onSearchIconPressed();

    void onSearchDataEntered(String data);

    void onLocationItemPressed(IFavoriteLocationItemDisplayModel dm);
}
