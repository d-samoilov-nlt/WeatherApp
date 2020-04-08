package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomMainWeatherResponse;
import com.google.gson.Gson;

public class MainWeatherResponseConverter {

    @TypeConverter
    public String fromRoomMainWeatherResponse(RoomMainWeatherResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomMainWeatherResponse toRoomMainWeatherResponse(String data) {
        return new Gson().fromJson(data, RoomMainWeatherResponse.class);
    }
}
