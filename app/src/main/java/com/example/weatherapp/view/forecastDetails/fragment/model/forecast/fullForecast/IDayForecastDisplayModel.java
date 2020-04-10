package com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast;

import java.util.List;

public interface IDayForecastDisplayModel {
    String getTitle();

    List<IOneTimeForecastDisplayModel> getForecastList();
}
