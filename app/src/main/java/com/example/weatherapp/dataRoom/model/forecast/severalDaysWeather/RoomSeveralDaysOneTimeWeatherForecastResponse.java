package com.example.weatherapp.dataRoom.model.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomCloudsResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomMainWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.weatherCondition.RoomWindResponse;
import com.example.weatherapp.dataRoom.model.typeConverter.WeatherResponseConverter;

import java.io.Serializable;

@Entity
public class RoomSeveralDaysOneTimeWeatherForecastResponse implements ISeveralDaysOneTimeWeatherForecastResponse, Serializable {
    @PrimaryKey
    @ColumnInfo(name = "city_name")
    private String cityName;

    @Embedded
    private RoomMainWeatherResponse main;

    @ColumnInfo(name = "weather_list")
    @TypeConverters({WeatherResponseConverter.class})
    private RoomWeatherResponse[] weatherList;

    @Embedded
    private RoomCloudsResponse clouds;

    @Embedded
    private RoomWindResponse wind;

    @ColumnInfo(name = "date")
    private String date;

    public RoomSeveralDaysOneTimeWeatherForecastResponse(ISeveralDaysOneTimeWeatherForecastResponse origin, String cityName) {
        this.weatherList = getWeatherResponseFromOrigin(origin.getWeatherList());
        this.main = new RoomMainWeatherResponse(origin.getMain());
        this.wind = new RoomWindResponse(origin.getWind());
        this.clouds = new RoomCloudsResponse(origin.getClouds());
        this.date = origin.getDate();
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public RoomMainWeatherResponse getMain() {
        return main;
    }

    public void setMain(RoomMainWeatherResponse main) {
        this.main = main;
    }

    @Override
    public RoomWeatherResponse[] getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(RoomWeatherResponse[] weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public RoomCloudsResponse getClouds() {
        return clouds;
    }

    public void setClouds(RoomCloudsResponse clouds) {
        this.clouds = clouds;
    }

    @Override
    public RoomWindResponse getWind() {
        return wind;
    }

    public void setWind(RoomWindResponse wind) {
        this.wind = wind;
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private RoomWeatherResponse[] getWeatherResponseFromOrigin(IWeatherResponse[] origin) {
        RoomWeatherResponse[] roomWeatherResponses = new RoomWeatherResponse[origin.length];
        for (int i = 0; i < origin.length; i++) {
            roomWeatherResponses[i] = new RoomWeatherResponse(origin[i]);
        }
        return roomWeatherResponses;
    }
}
