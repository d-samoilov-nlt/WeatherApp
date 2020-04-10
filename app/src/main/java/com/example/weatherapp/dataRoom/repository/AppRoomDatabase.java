package com.example.weatherapp.dataRoom.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.dataRoom.model.forecast.relation.RoomFavoriteLocationWithWeather;

@Database(entities = {RoomFavoriteLocationWithWeather.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract IFavoriteLocationDao favoriteLocationDao();
}
