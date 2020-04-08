package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather.RoomSeveralDaysWeatherResponse;
import com.google.gson.Gson;

public class SeveralDaysWeatherResponseConverter {
    @TypeConverter
    public String fromRoomSeveralDaysWeatherResponse(RoomSeveralDaysWeatherResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomSeveralDaysWeatherResponse toRoomSeveralDaysWeatherResponse(String data) {
        return new Gson().fromJson(data, RoomSeveralDaysWeatherResponse.class);
    }
}
