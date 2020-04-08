package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;

import java.io.Serializable;

@Entity
public class RoomCloudsResponse implements ICloudsResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "all")
    private int all;

    public RoomCloudsResponse(ICloudsResponse origin) {
        this.all = origin.getAll();
    }

    public RoomCloudsResponse(int id, int all) {
        this.id = id;
        this.all = all;
    }

    @Override
    public int getAll() {
        return all;
    }
}
