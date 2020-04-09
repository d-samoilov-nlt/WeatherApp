package com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;

import java.io.Serializable;

@Entity
public class RoomMainWeatherResponse implements IMainResponse, Serializable {
    @ColumnInfo(name = "temp")
    public double temp;
    @ColumnInfo(name = "pressure")
    public int pressure;
    @ColumnInfo(name = "humidity")
    public int humidity;
    @ColumnInfo(name = "temp_min")
    public double tempMin;
    @ColumnInfo(name = "temp_max")
    public double tempMax;

    public RoomMainWeatherResponse(IMainResponse origin) {
        this.temp = origin.getTemp();
        this.pressure = origin.getPressure();
        this.humidity = origin.getHumidity();
        this.tempMin = origin.getTempMin();
        this.tempMax = origin.getTempMax();
    }

    public RoomMainWeatherResponse(double temp, int pressure, int humidity, double tempMin, double tempMax) {
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

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    @Override
    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}
