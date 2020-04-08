package com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomCloudsResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomMainWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWindResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.CloudsResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.MainWeatherResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.WeatherResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.WindResponseConverter;

import java.io.Serializable;

@Entity
public class RoomSeveralDaysOneTimeWeatherForecastResponse implements ISeveralDaysOneTimeWeatherForecastResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "main")
    @TypeConverters({MainWeatherResponseConverter.class})
    private RoomMainWeatherResponse main;

    @ColumnInfo(name = "weatherArray")
    @TypeConverters({WeatherResponseConverter.class})
    private RoomWeatherResponse[] weatherArray;

    @ColumnInfo(name = "clouds")
    @TypeConverters({CloudsResponseConverter.class})
    private RoomCloudsResponse clouds;

    @ColumnInfo(name = "wind")
    @TypeConverters({WindResponseConverter.class})
    private RoomWindResponse wind;

    @ColumnInfo(name = "date")
    private String date;

    public RoomSeveralDaysOneTimeWeatherForecastResponse(ISeveralDaysOneTimeWeatherForecastResponse origin) {
        this.weatherArray = getWeatherResponseFromOrigin(origin.getWeather());
        this.main = new RoomMainWeatherResponse(origin.getMain());
        this.wind = new RoomWindResponse(origin.getWind());
        this.clouds = new RoomCloudsResponse(origin.getClouds());
        this.date = origin.getDate();
    }

    public RoomSeveralDaysOneTimeWeatherForecastResponse(int id, RoomMainWeatherResponse main, RoomWeatherResponse[] weatherArray, RoomCloudsResponse clouds, RoomWindResponse wind, String date) {
        this.id = id;
        this.main = main;
        this.weatherArray = weatherArray;
        this.clouds = clouds;
        this.wind = wind;
        this.date = date;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public IMainResponse getMain() {
        return main;
    }

    @Override
    public IWeatherResponse[] getWeather() {
        return weatherArray;
    }

    @Override
    public ICloudsResponse getClouds() {
        return clouds;
    }

    @Override
    public IWindResponse getWind() {
        return wind;
    }

    private RoomWeatherResponse[] getWeatherResponseFromOrigin(IWeatherResponse[] origin) {
        RoomWeatherResponse[] roomWeatherResponses = new RoomWeatherResponse[origin.length];
        for (int i = 0; i < origin.length; i++) {
            roomWeatherResponses[i] = new RoomWeatherResponse(origin[i]);
        }
        return roomWeatherResponses;
    }
}
