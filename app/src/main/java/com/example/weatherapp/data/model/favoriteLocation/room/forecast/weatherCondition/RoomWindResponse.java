package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;

import java.io.Serializable;

@Entity
public class RoomWindResponse implements IWindResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "speed")
    private double speed;
    @ColumnInfo(name = "degree")
    private int degree;

    public RoomWindResponse(IWindResponse origin) {
        this.speed = origin.getSpeed();
        this.degree = origin.getDegree();
    }

    public RoomWindResponse(int id, double speed, int degree) {
        this.id = id;
        this.speed = speed;
        this.degree = degree;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public int getDegree() {
        return degree;
    }
}
