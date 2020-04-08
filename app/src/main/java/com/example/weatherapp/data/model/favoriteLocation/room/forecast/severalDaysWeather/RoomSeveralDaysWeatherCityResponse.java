package com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;

@Entity
public class RoomSeveralDaysWeatherCityResponse implements ISeveralDaysWeatherCityResponse {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    public RoomSeveralDaysWeatherCityResponse(ISeveralDaysWeatherCityResponse origin) {
        this.name = origin.getName();
    }

    public RoomSeveralDaysWeatherCityResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
