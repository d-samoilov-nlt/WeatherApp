package com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityCoordinateResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICloudsResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IMainResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.currentWeather.IWindResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomCloudsResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomMainWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWindResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.CloudsResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.CoordinateResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.MainWeatherResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.WeatherResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.WindResponseConverter;

import java.io.Serializable;

@Entity
public class RoomCurrentWeatherResponse implements ICurrentWeatherResponse, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "weather")
    @TypeConverters({WeatherResponseConverter.class})
    private RoomWeatherResponse[] weather;

    @ColumnInfo(name = "main")
    @TypeConverters({MainWeatherResponseConverter.class})
    private RoomMainWeatherResponse main;

    @ColumnInfo(name = "wind")
    @TypeConverters({WindResponseConverter.class})
    private RoomWindResponse wind;

    @ColumnInfo(name = "clouds")
    @TypeConverters({CloudsResponseConverter.class})
    private RoomCloudsResponse clouds;

    @ColumnInfo(name = "name")
    private String cityName;

    @ColumnInfo(name = "responseCode")
    private int responseCode;

    @ColumnInfo(name = "coordinate")
    @TypeConverters({CoordinateResponseConverter.class})
    private RoomCoordinateResponse coordinateResponse;

    public RoomCurrentWeatherResponse(ICurrentWeatherResponse origin) {
        this.weather = getWeatherResponseFromOrigin(origin.getWeatherList());
        this.main = new RoomMainWeatherResponse(origin.getMain());
        this.wind = new RoomWindResponse(origin.getWind());
        this.clouds = new RoomCloudsResponse(origin.getClouds());
        this.cityName = origin.getCityName();
        this.responseCode = origin.getResponseCode();
        this.coordinateResponse = new RoomCoordinateResponse(origin.getCoordinate());
    }

    public RoomCurrentWeatherResponse(int id, RoomWeatherResponse[] weather, RoomMainWeatherResponse main, RoomWindResponse wind, RoomCloudsResponse clouds, String cityName, int responseCode, RoomCoordinateResponse coordinateResponse) {
        this.id = id;
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.cityName = cityName;
        this.responseCode = responseCode;
        this.coordinateResponse = coordinateResponse;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public ICityCoordinateResponse getCoordinate() {
        return coordinateResponse;
    }

    @Override
    public ICloudsResponse getClouds() {
        return clouds;
    }

    @Override
    public IMainResponse getMain() {
        return main;
    }

    @Override
    public IWeatherResponse[] getWeatherList() {
        return weather;
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
