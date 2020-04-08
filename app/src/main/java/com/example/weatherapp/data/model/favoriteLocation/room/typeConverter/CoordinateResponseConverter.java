package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.currentWeather.RoomCoordinateResponse;
import com.google.gson.Gson;

public class CoordinateResponseConverter {

    @TypeConverter
    public String fromRoomCoordinateResponse(RoomCoordinateResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomCoordinateResponse toRoomCoordinateResponse(String data) {
        return new Gson().fromJson(data, RoomCoordinateResponse.class);
    }
}
