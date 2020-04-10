package com.example.weatherapp.dataRoom.model.forecast.currentWeather;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomCloudsResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomMainWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomWindResponse;
import com.example.weatherapp.dataRoom.model.typeConverter.WeatherResponseConverter;

import java.io.Serializable;

public class RoomCurrentWeatherResponse implements ICurrentWeatherResponse, Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "weather_city_name")
    private String weatherResponseCityName;

    @ColumnInfo(name = "weather_list")
    @TypeConverters({WeatherResponseConverter.class})
    private RoomWeatherResponse[] weatherList;

    @Embedded
    private RoomMainWeatherResponse main;

    @Embedded
    private RoomWindResponse wind;

    @Embedded
    private RoomCloudsResponse clouds;

    @ColumnInfo(name = "response_code")
    private int responseCode;

    @Embedded
    public RoomCoordinateResponse coordinate;

    public RoomCurrentWeatherResponse(ICurrentWeatherResponse origin) {
        this.weatherResponseCityName = origin.getCityName();
        this.weatherList = getWeatherResponseFromOrigin(origin.getWeatherList());
        this.main = new RoomMainWeatherResponse(origin.getMain());
        this.wind = new RoomWindResponse(origin.getWind());
        this.clouds = new RoomCloudsResponse(origin.getClouds());
        this.responseCode = origin.getResponseCode();
        this.coordinate = new RoomCoordinateResponse(origin.getCoordinate());
    }

    public RoomCurrentWeatherResponse(@NonNull String weatherResponseCityName, RoomWeatherResponse[] weatherList, RoomMainWeatherResponse main, RoomWindResponse wind, RoomCloudsResponse clouds, int responseCode, RoomCoordinateResponse coordinate) {
        this.weatherResponseCityName = weatherResponseCityName;
        this.weatherList = weatherList;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.responseCode = responseCode;
        this.coordinate = coordinate;
    }

    @NonNull
    public String getWeatherResponseCityName() {
        return weatherResponseCityName;
    }

    public void setWeatherResponseCityName(@NonNull String weatherResponseCityName) {
        this.weatherResponseCityName = weatherResponseCityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.weatherResponseCityName = cityName;
    }

    @Override
    public RoomMainWeatherResponse getMain() {
        return main;
    }

    @Override
    public RoomWeatherResponse[] getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(RoomWeatherResponse[] weatherList) {
        this.weatherList = weatherList;
    }

    public void setMain(RoomMainWeatherResponse main) {
        this.main = main;
    }

    @Override
    public RoomWindResponse getWind() {
        return wind;
    }

    public void setWind(RoomWindResponse wind) {
        this.wind = wind;
    }

    @Override
    public RoomCloudsResponse getClouds() {
        return clouds;
    }

    public void setClouds(RoomCloudsResponse clouds) {
        this.clouds = clouds;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getCityName() {
        return weatherResponseCityName;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public RoomCoordinateResponse getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(RoomCoordinateResponse coordinate) {
        this.coordinate = coordinate;
    }

    private RoomWeatherResponse[] getWeatherResponseFromOrigin(IWeatherResponse[] origin) {
        RoomWeatherResponse[] roomWeatherResponses = new RoomWeatherResponse[origin.length];
        for (int i = 0; i < origin.length; i++) {
            roomWeatherResponses[i] = new RoomWeatherResponse(origin[i]);
        }
        return roomWeatherResponses;
    }
}
