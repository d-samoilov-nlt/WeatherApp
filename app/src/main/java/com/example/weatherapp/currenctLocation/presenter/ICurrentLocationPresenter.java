package com.example.weatherapp.currenctLocation.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public interface ICurrentLocationPresenter {
    void onCreate();

    void onStart();

    void onLocationUpdated(IDeviceLocation deviceLocation);

    void onTrySearchAgainPressed();
}
