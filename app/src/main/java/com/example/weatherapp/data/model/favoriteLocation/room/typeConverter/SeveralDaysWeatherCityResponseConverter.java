package com.example.weatherapp.data.model.favoriteLocation.room.typeConverter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.severalDaysWeather.RoomSeveralDaysWeatherCityResponse;
import com.google.gson.Gson;

public class SeveralDaysWeatherCityResponseConverter {

    @TypeConverter
    public String fromRoomMainWeatherResponse(RoomSeveralDaysWeatherCityResponse data) {
        return new Gson().toJson(data);
    }

    @TypeConverter
    public RoomSeveralDaysWeatherCityResponse toRoomMainWeatherResponse(String data) {
        return new Gson().fromJson(data, RoomSeveralDaysWeatherCityResponse.class);
    }
}
