package com.example.weatherapp.forecastDetails.view;

import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;

public interface IForecastDetailsView {
    void showForecastToday(IDayForecastDisplayModel todayForecastDisplayModel);
}
