package com.example.weatherapp.data.forecast.fullForecast;

import java.util.List;

public interface IDayForecastDisplayModel {
    String getTitle();

    List<IOneTimeForecastDisplayModel> getForecastList();
}
