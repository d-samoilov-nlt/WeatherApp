package com.example.weatherapp.data.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weatherapp.data.model.favoriteLocation.room.RoomFavoriteLocationCacheData;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface IFavoriteLocationDao {

    @Insert(onConflict = REPLACE)
    void save(RoomFavoriteLocationCacheData data);

    @Query("SELECT * FROM RoomFavoriteLocationCacheData")
    List<RoomFavoriteLocationCacheData> loadAll();

    @Query("SELECT * FROM RoomFavoriteLocationCacheData WHERE cityName == (:cityName)")
    RoomFavoriteLocationCacheData loadByCityName(String cityName);

    @Query("DELETE FROM RoomFavoriteLocationCacheData WHERE cityName == (:cityName)")
    void deleteByCityName(String cityName);
}
