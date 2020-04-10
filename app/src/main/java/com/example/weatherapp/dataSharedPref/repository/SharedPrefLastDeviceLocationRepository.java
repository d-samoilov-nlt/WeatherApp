package com.example.weatherapp.dataSharedPref.repository;

import android.content.SharedPreferences;

import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.repository.ILastDeviceLocationRepository;
import com.example.weatherapp.dataSharedPref.model.SerializableDeviceLocation;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.google.gson.Gson;

public class SharedPrefLastDeviceLocationRepository implements ILastDeviceLocationRepository {
    private final String LOCATION_KEY = "last_location";
    private final SharedPreferences sharedPreferences;


    public SharedPrefLastDeviceLocationRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void save(IDeviceLocation deviceLocation) {
        sharedPreferences.edit()
                .putString(
                        LOCATION_KEY,
                        new Gson().toJson(new SerializableDeviceLocation(deviceLocation))
                ).apply();
    }

    @Override
    public IDeviceLocation load() {

        String data = sharedPreferences.getString(LOCATION_KEY, "");

        if (data.isEmpty()) {
            throw new NotFoundException(LOCATION_KEY + " - not found");
        }

        return new Gson().fromJson(data, SerializableDeviceLocation.class);
    }
}
