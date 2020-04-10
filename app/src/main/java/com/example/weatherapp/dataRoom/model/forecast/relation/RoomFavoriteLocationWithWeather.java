package com.example.weatherapp.dataRoom.model.forecast.relation;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.dataRoom.model.forecast.currentWeather.RoomCurrentWeatherResponse;
import com.example.weatherapp.dataRoom.model.forecast.severalDaysWeather.RoomSeveralDaysWeatherResponse;

@Entity
public class RoomFavoriteLocationWithWeather implements IFavoriteLocationCacheData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "unit_type")
    private int forecastUnitType;

    @Embedded
    public RoomCurrentWeatherResponse currentWeather;

    @Embedded
    public RoomSeveralDaysWeatherResponse severalDaysForecast;

    public RoomFavoriteLocationWithWeather(@NonNull String cityName, int forecastUnitType, RoomCurrentWeatherResponse currentWeather, RoomSeveralDaysWeatherResponse severalDaysForecast) {
        this.cityName = cityName;
        this.forecastUnitType = forecastUnitType;
        this.currentWeather = currentWeather;
        this.severalDaysForecast = severalDaysForecast;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }

    public void setForecastUnitType(int forecastUnitType) {
        this.forecastUnitType = forecastUnitType;
    }

    public void setCurrentWeather(RoomCurrentWeatherResponse currentWeather) {
        this.currentWeather = currentWeather;
    }

    @NonNull
    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public int getForecastUnitType() {
        return forecastUnitType;
    }

    @Override
    public RoomCurrentWeatherResponse getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public RoomSeveralDaysWeatherResponse getSeveralDaysForecast() {
        return severalDaysForecast;
    }

    public void setSeveralDaysForecast(RoomSeveralDaysWeatherResponse severalDaysForecast) {
        this.severalDaysForecast = severalDaysForecast;
    }
}
