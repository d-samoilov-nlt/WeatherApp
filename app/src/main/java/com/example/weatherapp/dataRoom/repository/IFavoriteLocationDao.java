package com.example.weatherapp.dataRoom.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.weatherapp.dataRoom.model.forecast.relation.RoomFavoriteLocationWithWeather;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface IFavoriteLocationDao {

    @Transaction
    @Insert(onConflict = REPLACE)
    void save(RoomFavoriteLocationWithWeather data);

    @Transaction
    @Query("SELECT * FROM RoomFavoriteLocationWithWeather")
    List<RoomFavoriteLocationWithWeather> loadAll();

    @Transaction
    @Query("SELECT * FROM RoomFavoriteLocationWithWeather WHERE city_name == (:cityName)")
    RoomFavoriteLocationWithWeather loadByCityName(String cityName);

    @Transaction
    @Query("SELECT * FROM RoomFavoriteLocationWithWeather " +
            "WHERE CAST(latitude AS INT) LIKE (:lat) AND CAST(longitude AS INT) LIKE (:lon)")
    RoomFavoriteLocationWithWeather loadByCityLocation(int lat, int lon);

    @Transaction
    @Query("DELETE FROM RoomFavoriteLocationWithWeather WHERE city_name == (:cityName)")
    void deleteByCityName(String cityName);
}
