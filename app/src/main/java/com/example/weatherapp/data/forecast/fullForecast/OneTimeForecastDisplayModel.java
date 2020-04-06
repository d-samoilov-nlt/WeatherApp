package com.example.weatherapp.data.forecast.fullForecast;

import android.net.Uri;

public class OneTimeForecastDisplayModel implements IOneTimeForecastDisplayModel {
    private final String temp;
    private final String time;
    private final String pressure;
    private final String windSpeed;
    private final String humidity;
    private final Uri weatherViewImageUrl;

    public OneTimeForecastDisplayModel(
            String temp,
            String time,
            String pressure,
            String windSpeed,
            String humidity,
            Uri weatherViewImageUrl) {

        this.temp = temp;
        this.time = time;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.weatherViewImageUrl = weatherViewImageUrl;
    }

    @Override
    public String getTemp() {
        return temp;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getPressure() {
        return pressure;
    }

    @Override
    public String getWind() {
        return windSpeed;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    @Override
    public Uri getWeatherImageUrl() {
        return weatherViewImageUrl;
    }
}
