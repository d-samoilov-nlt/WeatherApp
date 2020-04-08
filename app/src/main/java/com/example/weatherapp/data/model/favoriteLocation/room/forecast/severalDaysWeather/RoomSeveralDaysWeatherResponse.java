package com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.SeveralDaysOneTimeWeatherForecastResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.SeveralDaysWeatherCityResponseConverter;

import java.io.Serializable;

@Entity
public class RoomSeveralDaysWeatherResponse implements ISeveralDaysWeatherResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "code")
    private int code;

    @ColumnInfo(name = "forecastList")
    @TypeConverters({SeveralDaysOneTimeWeatherForecastResponseConverter.class})
    private RoomSeveralDaysOneTimeWeatherForecastResponse[] forecastList;

    @ColumnInfo(name = "city")
    @TypeConverters({SeveralDaysWeatherCityResponseConverter.class})
    private RoomSeveralDaysWeatherCityResponse city;

    public RoomSeveralDaysWeatherResponse(ISeveralDaysWeatherResponse origin) {
        this.code = origin.getResponseCode();
        this.forecastList = getForecastListFromOrigin(origin.getForecastList());
        this.city = new RoomSeveralDaysWeatherCityResponse(origin.getCity());
    }

    public RoomSeveralDaysWeatherResponse(int id, int code, RoomSeveralDaysOneTimeWeatherForecastResponse[] forecastList, RoomSeveralDaysWeatherCityResponse city) {
        this.id = id;
        this.code = code;
        this.forecastList = forecastList;
        this.city = city;
    }

    @Override
    public int getResponseCode() {
        return code;
    }

    @Override
    public ISeveralDaysWeatherCityResponse getCity() {
        return city;
    }

    @Override
    public ISeveralDaysOneTimeWeatherForecastResponse[] getForecastList() {
        return forecastList;
    }

    private RoomSeveralDaysOneTimeWeatherForecastResponse[] getForecastListFromOrigin(ISeveralDaysOneTimeWeatherForecastResponse[] origin) {
        RoomSeveralDaysOneTimeWeatherForecastResponse[] roomWeatherResponses = new RoomSeveralDaysOneTimeWeatherForecastResponse[origin.length];
        for (int i = 0; i < origin.length; i++) {
            roomWeatherResponses[i] = new RoomSeveralDaysOneTimeWeatherForecastResponse(origin[i]);
        }
        return roomWeatherResponses;
    }
}
