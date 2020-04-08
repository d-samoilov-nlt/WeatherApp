package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather.RoomCurrentWeatherResponse;
import com.google.gson.Gson;

public class CurrentWeatherResponseConverter {

    @TypeConverter
    public String fromRoomCurrentWeatherResponse(RoomCurrentWeatherResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomCurrentWeatherResponse toRoomCurrentWeatherResponse(String data) {
        return new Gson().fromJson(data, RoomCurrentWeatherResponse.class);
    }
}
