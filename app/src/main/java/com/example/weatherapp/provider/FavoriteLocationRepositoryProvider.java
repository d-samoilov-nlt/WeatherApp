package com.example.weatherapp.provider;

import android.content.Context;

import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.data.repository.RoomFavoriteLocationRepository;

public class FavoriteLocationRepositoryProvider {
    private static IFavoriteLocationRepository repository;

    public static IFavoriteLocationRepository get(Context context) {
        if (repository == null) {
            synchronized (FavoriteLocationRepositoryProvider.class) {
                if (repository == null) {
                    repository =
                            new RoomFavoriteLocationRepository(
                                    RoomDBProvider.get(context).favoriteLocationDao());
                }
            }
        }
        return repository;
    }
}
