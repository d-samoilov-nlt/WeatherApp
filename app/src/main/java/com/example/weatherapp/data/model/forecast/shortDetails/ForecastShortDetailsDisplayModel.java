package com.example.weatherapp.data.model.forecast.shortDetails;

import com.example.weatherapp.data.model.favoriteLocation.FavoriteLocationUtilsType;

public class ForecastShortDetailsDisplayModel implements IForecastShortDetailsDisplayModel {
    private final String cityName;
    private final String temperature;
    private final String forecast;
    private final int utilsType;

    public ForecastShortDetailsDisplayModel(String cityName, String temperature, String forecast) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.forecast = forecast;
        this.utilsType = FavoriteLocationUtilsType.CELSIUS.getValue();
    }

    public ForecastShortDetailsDisplayModel(String cityName, String temperature, String forecast, int utilsType) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.forecast = forecast;
        this.utilsType = utilsType;
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
    public int getUtilsType() {
        return utilsType;
    }
}
