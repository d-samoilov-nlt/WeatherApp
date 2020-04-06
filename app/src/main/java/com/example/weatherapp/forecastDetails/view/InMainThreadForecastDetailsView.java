package com.example.weatherapp.forecastDetails.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;

public class InMainThreadForecastDetailsView implements IForecastDetailsView {
    private final IForecastDetailsView origin;
    private final Handler handler;

    public InMainThreadForecastDetailsView(IForecastDetailsView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showForecastToday(IDayForecastDisplayModel todayForecastDisplayModel) {
        handler.post(() -> origin.showForecastToday(todayForecastDisplayModel));
    }
}
