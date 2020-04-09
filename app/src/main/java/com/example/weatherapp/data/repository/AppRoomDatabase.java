package com.example.weatherapp.data.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.data.model.favoriteLocation.room.forecast.relation.RoomFavoriteLocationWithWeather;

@Database(entities = {RoomFavoriteLocationWithWeather.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract IFavoriteLocationDao favoriteLocationDao();
}
