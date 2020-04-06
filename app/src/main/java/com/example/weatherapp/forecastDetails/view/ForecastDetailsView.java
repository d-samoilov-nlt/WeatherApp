package com.example.weatherapp.forecastDetails.view;

import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;

public class ForecastDetailsView implements IForecastDetailsView {
    private final ForecastDetailsRVAdapter forecastDetailsRVAdapter;

    public ForecastDetailsView(ForecastDetailsRVAdapter forecastDetailsRVAdapter) {
        this.forecastDetailsRVAdapter = forecastDetailsRVAdapter;
    }

    @Override
    public void showForecastToday(IDayForecastDisplayModel dm) {
        forecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        forecastDetailsRVAdapter.notifyDataSetChanged();
    }
}
