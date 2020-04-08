package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomWindResponse;
import com.google.gson.Gson;

public class WindResponseConverter {
    @TypeConverter
    public String fromRoomWindResponse(RoomWindResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomWindResponse toRoomWindResponse(String data) {
        return new Gson().fromJson(data, RoomWindResponse.class);
    }
}
