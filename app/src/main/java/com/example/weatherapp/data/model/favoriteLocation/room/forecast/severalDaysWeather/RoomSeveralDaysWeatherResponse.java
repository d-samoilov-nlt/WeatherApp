package com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysOneTimeWeatherForecastResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherCityResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.SeveralDaysOneTimeWeatherForecastResponseConverter;

public class RoomSeveralDaysWeatherResponse implements ISeveralDaysWeatherResponse {
    @PrimaryKey
    @ColumnInfo(name = "several_days_weather_city_name")
    private String severalDaysWeatherCityName;

    @ColumnInfo(name = "code")
    private int responseCode;

    @ColumnInfo(name = "forecastList")
    @TypeConverters({SeveralDaysOneTimeWeatherForecastResponseConverter.class})
    private RoomSeveralDaysOneTimeWeatherForecastResponse[] forecastList;

    public RoomSeveralDaysWeatherResponse(ISeveralDaysWeatherResponse origin) {
        this.responseCode = origin.getResponseCode();
        this.severalDaysWeatherCityName = origin.getCity().getName();
        this.forecastList = getForecastListFromOrigin(origin.getForecastList());
    }

    public RoomSeveralDaysWeatherResponse(String severalDaysWeatherCityName, int responseCode, RoomSeveralDaysOneTimeWeatherForecastResponse[] forecastList) {
        this.severalDaysWeatherCityName = severalDaysWeatherCityName;
        this.responseCode = responseCode;
        this.forecastList = forecastList;
    }

    public String getSeveralDaysWeatherCityName() {
        return severalDaysWeatherCityName;
    }

    public void setSeveralDaysWeatherCityName(String severalDaysWeatherCityName) {
        this.severalDaysWeatherCityName = severalDaysWeatherCityName;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public ISeveralDaysWeatherCityResponse getCity() {
        return new RoomSeveralDaysWeatherCityResponse(severalDaysWeatherCityName);
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public RoomSeveralDaysOneTimeWeatherForecastResponse[] getForecastList() {
        return forecastList;
    }

    public void setForecastList(RoomSeveralDaysOneTimeWeatherForecastResponse[] forecastList) {
        this.forecastList = forecastList;
    }

    private RoomSeveralDaysOneTimeWeatherForecastResponse[] getForecastListFromOrigin(ISeveralDaysOneTimeWeatherForecastResponse[] origin) {
        RoomSeveralDaysOneTimeWeatherForecastResponse[] roomWeatherResponses = new RoomSeveralDaysOneTimeWeatherForecastResponse[origin.length];
        for (int i = 0; i < origin.length; i++) {
            roomWeatherResponses[i] = new RoomSeveralDaysOneTimeWeatherForecastResponse(origin[i], severalDaysWeatherCityName);
        }
        return roomWeatherResponses;
    }
}
