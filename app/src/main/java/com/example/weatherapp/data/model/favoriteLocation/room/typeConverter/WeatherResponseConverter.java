package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWeatherResponse;
import com.google.gson.Gson;

public class WeatherResponseConverter {
    @TypeConverter
    public String fromRoomWeatherResponse(RoomWeatherResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomWeatherResponse toRoomWeatherResponse(String data) {
        return new Gson().fromJson(data, RoomWeatherResponse.class);
    }
}
