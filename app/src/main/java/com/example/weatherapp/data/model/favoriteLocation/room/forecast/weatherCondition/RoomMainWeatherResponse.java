package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;

import java.io.Serializable;

@Entity
public class RoomMainWeatherResponse implements IMainResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "temp")
    private double temp;
    @ColumnInfo(name = "pressure")
    private int pressure;
    @ColumnInfo(name = "humidity")
    private int humidity;
    @ColumnInfo(name = "tempMin")
    private double tempMin;
    @ColumnInfo(name = "tempMax")
    private double tempMax;

    public RoomMainWeatherResponse(IMainResponse origin) {
        this.temp = origin.getTemp();
        this.pressure = origin.getPressure();
        this.humidity = origin.getHumidity();
        this.tempMin = origin.getTempMin();
        this.tempMax = origin.getTempMax();
    }

    public RoomMainWeatherResponse(int id, double temp, int pressure, int humidity, double tempMin, double tempMax) {
        this.id = id;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    @Override
    public double getTemp() {
        return temp;
    }

    @Override
    public int getPressure() {
        return pressure;
    }

    @Override
    public int getHumidity() {
        return humidity;
    }

    @Override
    public double getTempMin() {
        return tempMin;
    }

    @Override
    public double getTempMax() {
        return tempMax;
    }
}
