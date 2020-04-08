package com.example.weatherapp.data.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.weatherapp.data.model.favoriteLocation.room.RoomFavoriteLocationCacheData;

@Database(entities = {RoomFavoriteLocationCacheData.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract IFavoriteLocationDao favoriteLocationDao();
}
