package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.weatherCondition.RoomCloudsResponse;
import com.google.gson.Gson;

public class CloudsResponseConverter {

    @TypeConverter
    public String fromRoomCloudsResponse(RoomCloudsResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomCloudsResponse toRoomCloudsResponse(String data) {
        return new Gson().fromJson(data, RoomCloudsResponse.class);
    }
}
