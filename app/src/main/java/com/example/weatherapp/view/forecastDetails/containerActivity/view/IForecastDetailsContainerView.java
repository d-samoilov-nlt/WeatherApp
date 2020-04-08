package com.example.weatherapp.view.forecastDetails.containerActivity.view;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IForecastDetailsContainerView {
    void showForecastDetails(IDeviceLocation deviceLocation);
    void updateForecastDetails(IDeviceLocation deviceLocation);
    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);
}
