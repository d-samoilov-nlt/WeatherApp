package com.example.weatherapp.view.forecastDetails.containerActivity.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class InMainForecastDetailsContainerView implements IForecastDetailsContainerView {
    private final IForecastDetailsContainerView origin;
    private final Handler handler;

    public InMainForecastDetailsContainerView(IForecastDetailsContainerView origin) {
        this.origin = origin;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        handler.post(() -> origin.showShortForecastDetails(dm));
    }
}
