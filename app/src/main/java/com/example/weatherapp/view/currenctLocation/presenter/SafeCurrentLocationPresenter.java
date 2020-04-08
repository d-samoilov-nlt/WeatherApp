package com.example.weatherapp.view.currenctLocation.presenter;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public class SafeCurrentLocationPresenter implements ICurrentLocationPresenter {
    private final ICurrentLocationPresenter origin;

    public SafeCurrentLocationPresenter(ICurrentLocationPresenter origin) {
        this.origin = origin;
    }

    @Override
    public void onCreate() {
        try {
            origin.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        try {
            origin.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationUpdated(IDeviceLocation deviceLocation) {
        try {
            origin.onLocationUpdated(deviceLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTrySearchAgainPressed() {
        try {
            origin.onTrySearchAgainPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
