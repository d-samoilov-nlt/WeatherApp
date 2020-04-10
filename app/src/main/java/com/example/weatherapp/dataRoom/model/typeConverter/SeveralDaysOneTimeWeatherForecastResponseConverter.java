package com.example.weatherapp.dataRoom.model.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.dataRoom.model.forecast.severalDaysWeather.RoomSeveralDaysOneTimeWeatherForecastResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SeveralDaysOneTimeWeatherForecastResponseConverter {

    @TypeConverter
    public String fromRoomMainWeatherResponse(RoomSeveralDaysOneTimeWeatherForecastResponse[] data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomSeveralDaysOneTimeWeatherForecastResponse[] toRoomMainWeatherResponse(String data) {
        Type type =
                new TypeToken<RoomSeveralDaysOneTimeWeatherForecastResponse[]>() {}.getType();
        return new Gson().fromJson(data, type);
    }
}
