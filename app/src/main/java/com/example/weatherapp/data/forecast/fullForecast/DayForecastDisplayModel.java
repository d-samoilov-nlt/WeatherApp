package com.example.weatherapp.data.forecast.fullForecast;

import java.util.List;

public class DayForecastDisplayModel implements IDayForecastDisplayModel {
    private final List<IOneTimeForecastDisplayModel> forecastDisplayModels;

    public DayForecastDisplayModel(
            List<IOneTimeForecastDisplayModel> forecastDisplayModels) {

        this.forecastDisplayModels = forecastDisplayModels;
    }

    @Override
    public List<IOneTimeForecastDisplayModel> getForecastList() {
        return forecastDisplayModels;
    }
}
