package com.example.weatherapi.data.entity.gson.currentWeather;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeather;
import com.google.gson.annotations.SerializedName;

public class GsonWeather implements IWeather {
    @SerializedName("id")
    private int id;
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String iconName;


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
