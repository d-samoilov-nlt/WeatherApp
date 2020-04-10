package com.example.weatherapp.data.repository;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;

public interface ILastDeviceLocationRepository {
    void save(IDeviceLocation deviceLocation);

    IDeviceLocation load();
}
