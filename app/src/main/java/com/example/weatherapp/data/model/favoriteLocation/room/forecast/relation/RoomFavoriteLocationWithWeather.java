package com.example.weatherapp.data.model.favoriteLocation.room.forecast.relation;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.room.RoomFavoriteLocation;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather.RoomCurrentWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather.RoomSeveralDaysWeatherResponse;

@Entity
public class RoomFavoriteLocationWithWeather implements IFavoriteLocationCacheData {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @Embedded
    public RoomFavoriteLocation cacheData;

    @Embedded
    public RoomCurrentWeatherResponse currentWeather;

    @Embedded
    public RoomSeveralDaysWeatherResponse severalDaysForecast;

    public RoomFavoriteLocationWithWeather(RoomFavoriteLocation cacheData, RoomCurrentWeatherResponse currentWeather, RoomSeveralDaysWeatherResponse severalDaysForecast) {
        this.cacheData = cacheData;
        this.currentWeather = currentWeather;
        this.severalDaysForecast = severalDaysForecast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCurrentWeather(RoomCurrentWeatherResponse currentWeather) {
        this.currentWeather = currentWeather;
    }

    public RoomFavoriteLocation getCacheData() {
        return cacheData;
    }

    public void setCacheData(RoomFavoriteLocation cacheData) {
        this.cacheData = cacheData;
    }


    public void setCurrentWeatherResponse(RoomCurrentWeatherResponse currentWeather) {
        this.currentWeather = currentWeather;
    }

    @Override
    public String getCityName() {
        return cacheData.getCityName();
    }

    @Override
    public int getForecastUnitType() {
        return cacheData.getForecastUnitType();
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
