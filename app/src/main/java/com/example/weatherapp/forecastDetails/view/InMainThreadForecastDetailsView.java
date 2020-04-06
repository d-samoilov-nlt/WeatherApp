package com.example.weatherapp.forecastDetails.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.data.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public class InMainThreadForecastDetailsView implements IForecastDetailsView {
    private final IForecastDetailsView origin;
    private final Handler handler;

    public InMainThreadForecastDetailsView(IForecastDetailsView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showForecastToday(IDayForecastDisplayModel dm) {
        handler.post(() -> origin.showForecastToday(dm));
    }

    @Override
    public void showForecastTomorrow(IDayForecastDisplayModel dm) {
        handler.post(() -> origin.showForecastTomorrow(dm));
    }

    @Override
    public void showForecastForSeveralDays(ISeveralDaysForecastDisplayModel dm) {
        handler.post(() -> origin.showForecastForSeveralDays(dm));
    }

    @Override
    public void showDetailsLoadingProgress(boolean isLoading) {
        handler.post(() -> origin.showDetailsLoadingProgress(isLoading));
    }

    @Override
    public void showLoadingError(boolean isError) {
        handler.post(() -> origin.showLoadingError(isError));
    }
}
