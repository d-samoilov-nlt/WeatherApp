package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWeatherResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WeatherResponseConverter {
    @TypeConverter
    public String fromRoomWeatherResponse(RoomWeatherResponse[] data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomWeatherResponse[] toRoomWeatherResponse(String data) {
        Type type =
                new TypeToken<RoomWeatherResponse[]>() {
                }.getType();
        return new Gson().fromJson(data, type);
    }
}
