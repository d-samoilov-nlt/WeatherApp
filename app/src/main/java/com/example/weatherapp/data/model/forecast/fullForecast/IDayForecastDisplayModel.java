package com.example.weatherapp.data.model.forecast.fullForecast;

import java.util.List;

public interface IDayForecastDisplayModel {
    String getTitle();

    List<IOneTimeForecastDisplayModel> getForecastList();
}
