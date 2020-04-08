package com.example.weatherapp.data.model.favoriteLocation.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather.RoomCurrentWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather.RoomSeveralDaysWeatherResponse;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.CurrentWeatherResponseConverter;
import com.example.weatherapp.data.model.favoriteLocation.room.typeConverter.SeveralDaysWeatherResponseConverter;

@Entity
public class RoomFavoriteLocationCacheData implements IFavoriteLocationCacheData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cityName")
    private String cityName;

    @ColumnInfo(name = "unitType")
    private int forecastUnitType;

    @ColumnInfo(name = "currentWeatherResponse")
    @TypeConverters({CurrentWeatherResponseConverter.class})
    private RoomCurrentWeatherResponse currentWeather;

    @ColumnInfo(name = "severalDaysWeatherResponse")
    @TypeConverters({SeveralDaysWeatherResponseConverter.class})
    private RoomSeveralDaysWeatherResponse severalDaysForecast;

    public RoomFavoriteLocationCacheData(IFavoriteLocationCacheData origin) {
        this.forecastUnitType = origin.getForecastUnitType();
        this.cityName = origin.getCityName();
        this.currentWeather = new RoomCurrentWeatherResponse(origin.getCurrentWeather());
        this.severalDaysForecast = new RoomSeveralDaysWeatherResponse(origin.getSeveralDaysForecast());
    }

    public RoomFavoriteLocationCacheData(@NonNull String cityName, int forecastUnitType, RoomCurrentWeatherResponse currentWeather, RoomSeveralDaysWeatherResponse severalDaysForecast) {
        this.cityName = cityName;
        this.forecastUnitType = forecastUnitType;
        this.currentWeather = currentWeather;
        this.severalDaysForecast = severalDaysForecast;
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
}
