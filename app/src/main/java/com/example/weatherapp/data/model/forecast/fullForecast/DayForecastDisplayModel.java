package com.example.weatherapp.data.model.forecast.fullForecast;

import java.util.List;

public class DayForecastDisplayModel implements IDayForecastDisplayModel {
    private final List<IOneTimeForecastDisplayModel> forecastDisplayModels;
    private final String title;

    public DayForecastDisplayModel(
            List<IOneTimeForecastDisplayModel> forecastDisplayModels,
            String title) {

        this.forecastDisplayModels = forecastDisplayModels;
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<IOneTimeForecastDisplayModel> getForecastList() {
        return forecastDisplayModels;
    }
}
