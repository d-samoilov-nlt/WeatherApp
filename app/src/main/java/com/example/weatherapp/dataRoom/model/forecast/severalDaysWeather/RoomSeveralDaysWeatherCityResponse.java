package com.example.weatherapp.dataRoom.model.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;

public class RoomSeveralDaysWeatherCityResponse implements ISeveralDaysWeatherCityResponse {

    @ColumnInfo(name = "name")
    private String name;

    public RoomSeveralDaysWeatherCityResponse(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
