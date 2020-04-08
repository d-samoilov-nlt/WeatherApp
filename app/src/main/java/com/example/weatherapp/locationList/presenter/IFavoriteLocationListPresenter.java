package com.example.weatherapp.locationList.presenter;

import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

public interface IFavoriteLocationListPresenter {
    void onStart();

    void onSearchIconPressed();

    void onSearchDataEntered(String data);

    void onLocationItemPressed(IFavoriteLocationItemDisplayModel dm);
}
