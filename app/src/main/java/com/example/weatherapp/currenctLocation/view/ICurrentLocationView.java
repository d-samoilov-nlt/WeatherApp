package com.example.weatherapp.currenctLocation.view;

public interface ICurrentLocationView {
    void startLocationService();

    void setIsSearchingLocationProcess(boolean isProcess);

    void setIsPermissionRequiredError(boolean isError);
}
