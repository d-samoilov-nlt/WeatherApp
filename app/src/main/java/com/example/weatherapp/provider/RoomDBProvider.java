package com.example.weatherapp.provider;

import android.content.Context;

import androidx.room.Room;

import com.example.weatherapp.data.repository.AppRoomDatabase;

public class RoomDBProvider {
    private static AppRoomDatabase appDatabase;

    public static AppRoomDatabase get(Context context) {
        if (appDatabase == null) {
            synchronized (RoomDBProvider.class) {
                if (appDatabase == null) {
                    appDatabase =
                            Room.databaseBuilder(
                                    context,
                                    AppRoomDatabase.class,
                                    "weather_db")
                                    .build();
                }
            }
        }
        return appDatabase;
    }
}
