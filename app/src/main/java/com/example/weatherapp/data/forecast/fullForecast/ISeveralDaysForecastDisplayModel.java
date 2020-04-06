package com.example.weatherapp.data.forecast.fullForecast;

import java.util.List;

public interface ISeveralDaysForecastDisplayModel {
    String getTitle();

    List<IDayForecastDisplayModel> getDaysForecastList();
}
