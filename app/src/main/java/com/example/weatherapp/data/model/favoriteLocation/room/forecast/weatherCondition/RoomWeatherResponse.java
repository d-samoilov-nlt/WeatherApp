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
    @ColumnInfo(name = "icon_name")
    private String iconName;

    public RoomWeatherResponse(IWeatherResponse origin) {
        this.main = origin.getMain();
        this.description = origin.getDescription();
        this.iconName = origin.getIconName();
        this.id = origin.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
