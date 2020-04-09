package com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityCoordinateResponse;

import java.io.Serializable;

@Entity
public class RoomCoordinateResponse implements ICityCoordinateResponse, Serializable {

    @ColumnInfo(name = "longitude")
    private double longitude;
    @ColumnInfo(name = "latitude")
    private double latitude;

    public RoomCoordinateResponse(ICityCoordinateResponse origin) {
        this.longitude = origin.getLongitude();
        this.latitude = origin.getLatitude();
    }

    public RoomCoordinateResponse(double longitude, double latitude) {
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
