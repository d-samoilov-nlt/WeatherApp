package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;

import java.io.Serializable;

@Entity
public class RoomWeatherResponse implements IWeatherResponse, Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "main")
    private String main;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "iconName")
    private String iconName;

    public RoomWeatherResponse(IWeatherResponse origin) {
        this.main = origin.getMain();
        this.description = origin.getDescription();
        this.iconName = origin.getIconName();
        this.id = origin.getId();
    }

    public RoomWeatherResponse(int id, String main, String description, String iconName) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.iconName = iconName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMain() {
        return main;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getIconName() {
        return iconName;
    }
}
