package com.example.weatherapp.currenctLocation.view;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface ICurrentLocationView {
    void startLocationService();

    void setIsSearchingLocationProcess(boolean isProcess);

    void setIsPermissionRequiredError(boolean isError);

    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);

    void showForecastDetails(IDeviceLocation deviceLocation);
}
