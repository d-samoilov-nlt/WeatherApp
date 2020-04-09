package com.example.weatherapp.domain.useCase.updateFavoriteLocation;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.weatherapp.service.UpdateFavoriteLocationForecastWorker;

import java.util.concurrent.TimeUnit;

public class EnqueueUpdatingFavoriteLocationWorkerUseCase implements IEnqueueUpdatingFavoriteLocationWorkerUseCase {

    @Override
    public void enqueue() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        PeriodicWorkRequest updateFavoriteLocationRequest =
                new PeriodicWorkRequest
                        .Builder
                        (
                                UpdateFavoriteLocationForecastWorker.class,
                                12,
                                TimeUnit.HOURS

                        ).setConstraints(constraints).build();

        WorkManager.getInstance().enqueue(updateFavoriteLocationRequest);
    }
}
