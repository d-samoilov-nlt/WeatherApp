package com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityCoordinateResponse;

import java.io.Serializable;

public class RoomCoordinateResponse implements ICityCoordinateResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "latitude")
    private double latitude;

    public RoomCoordinateResponse(ICityCoordinateResponse origin) {
        this.longitude = origin.getLongitude();
        this.latitude = origin.getLatitude();
    }

    public RoomCoordinateResponse(int id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }
}
