package com.example.weatherapp.data.forecast.shortDetails;

public class ForecastShortDetailsDisplayModel implements IForecastShortDetailsDisplayModel {
    private final String cityName;
    private final String temperature;
    private final String forecast;

    public ForecastShortDetailsDisplayModel(String cityName, String temperature, String forecast) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.forecast = forecast;
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
}
