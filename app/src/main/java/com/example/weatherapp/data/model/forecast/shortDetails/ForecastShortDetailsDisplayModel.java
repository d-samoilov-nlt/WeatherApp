package com.example.weatherapp.data.model.forecast.shortDetails;

import com.example.weatherapi.data.ForecastUnitsType;

public class ForecastShortDetailsDisplayModel implements IForecastShortDetailsDisplayModel {
    private final String cityName;
    private final String temperature;
    private final String forecast;
    private final int forecastUnitType;

    public ForecastShortDetailsDisplayModel(String cityName, String temperature, String forecast) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.forecast = forecast;
        this.forecastUnitType = ForecastUnitsType.CELSIUS.getValue();
    }

    public ForecastShortDetailsDisplayModel(String cityName, String temperature, String forecast, int forecastUnitType) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.forecast = forecast;
        this.forecastUnitType = forecastUnitType;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getTemp() {
        return temperature;
    }

    @Override
    public String getForecast() {
        return forecast;
    }

    @Override
    public int getForecastUnitType() {
        return forecastUnitType;
    }
}
