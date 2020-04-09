package com.example.weatherapp.data.model.favoriteLocation.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomFavoriteLocation {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "unit_type")
    private int forecastUnitType;

    public RoomFavoriteLocation(@NonNull String cityName, int forecastUnitType) {
        this.cityName = cityName;
        this.forecastUnitType = forecastUnitType;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public int getForecastUnitType() {
        return forecastUnitType;
    }
}
