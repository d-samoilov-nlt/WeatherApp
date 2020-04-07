package com.example.weatherapp.data.model.forecast.fullForecast;

import java.util.List;

public class SeveralDaysForecastDisplayModel implements ISeveralDaysForecastDisplayModel {
    private final List<IDayForecastDisplayModel> dayForecastDisplayModels;

    public SeveralDaysForecastDisplayModel(List<IDayForecastDisplayModel> dayForecastDisplayModels) {
        this.dayForecastDisplayModels = dayForecastDisplayModels;
    }

    @Override
    public List<IDayForecastDisplayModel> getDaysForecastList() {
        return dayForecastDisplayModels;
    }
}
