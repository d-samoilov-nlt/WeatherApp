package com.example.weatherapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.data.entity.pojo.CityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.GetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.IGetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.IGetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationCacheData;
import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

public class UpdateFavoriteLocationForecastWorker extends Worker {
    private final int NOTIFICATION_ID = 1;

    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IGetSeveralDaysForecastUseCase getSeveralDaysForecastUseCase;
    private final IGetCurrentWeatherByCityLocationUseCase getCurrentWeatherByCityLocationUseCase;

    public UpdateFavoriteLocationForecastWorker(
            @NonNull Context context,
            @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

        favoriteLocationRepository =
                FavoriteLocationRepositoryProvider.get(
                        getApplicationContext());
        getSeveralDaysForecastUseCase =
                new GetSeveralDaysForecastUseCase(
                        OpenWeatherApiProvider.get(getApplicationContext()));
        getCurrentWeatherByCityLocationUseCase =
                new GetCurrentWeatherByCityLocationUseCase(
                        OpenWeatherApiProvider.get(getApplicationContext()));
    }

    @NonNull
    @Override
    public Result doWork() {
        showNotification();

        try {
            List<IFavoriteLocationCacheData> favoriteLocationCacheDataList = favoriteLocationRepository.loadAll();

            for (IFavoriteLocationCacheData cacheData : favoriteLocationCacheDataList) {
                ICityLocation cityLocation = new CityLocation(
                        cacheData.getCurrentWeather().getCoordinate().getLongitude(),
                        cacheData.getCurrentWeather().getCoordinate().getLatitude());

                IFavoriteLocationCacheData updatedCacheData =
                        new FavoriteLocationCacheData(
                                cacheData.getForecastUnitType(),
                                cacheData.getCityName(),
                                getCurrentWeatherByCityLocationUseCase.get(cityLocation, cacheData.getForecastUnitType()),
                                getSeveralDaysForecastUseCase.get(cityLocation, cacheData.getForecastUnitType())
                        );

                favoriteLocationRepository.save(updatedCacheData);

            }
        } catch (NotFoundException ignore) {
            // nop
        } finally {
            hideNotification();
        }

        return Result.success();
    }

    private void showNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "UpdateFavoriteLocationForecastWorker";
        String NOTIFICATION_NAME = "weather_app";
        String NOTIFICATION_DESCRIPTION = "updating_info";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel =
                    new NotificationChannel(
                            NOTIFICATION_CHANNEL_ID,
                            NOTIFICATION_NAME,
                            importance);
            mChannel.setDescription(NOTIFICATION_DESCRIPTION);
            mChannel.setShowBadge(true);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(
                                getApplicationContext()
                                        .getResources()
                                        .getString(R.string.notification_update_favorite_forecast_title))
                        .setContentText(
                                getApplicationContext()
                                        .getResources()
                                        .getString(R.string.notification_update_favorite_forecast_content));

        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    private void hideNotification() {
        NotificationManager notificationManager =
                (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
