package com.example.weatherapp.dataRoom.model.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;

import java.io.Serializable;

@Entity
public class RoomWindResponse implements IWindResponse, Serializable {

    @ColumnInfo(name = "speed")
    private double speed;
    @ColumnInfo(name = "degree")
    private int degree;

    public RoomWindResponse(IWindResponse origin) {
        this.speed = origin.getSpeed();
        this.degree = origin.getDegree();
    }

    public RoomWindResponse(double speed, int degree) {
        this.speed = speed;
        this.degree = degree;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
