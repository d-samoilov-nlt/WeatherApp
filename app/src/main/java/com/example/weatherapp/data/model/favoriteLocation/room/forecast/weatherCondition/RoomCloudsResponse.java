package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;

import java.io.Serializable;

@Entity
public class RoomCloudsResponse implements ICloudsResponse, Serializable {
    @ColumnInfo(name = "all")
    private int all;

    public RoomCloudsResponse(ICloudsResponse origin) {
        this.all = origin.getAll();
    }

    public RoomCloudsResponse(int all) {
        this.all = all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public int getAll() {
        return all;
    }
}
