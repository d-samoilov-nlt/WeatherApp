package com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast;

import android.net.Uri;

public interface IOneTimeForecastDisplayModel {
    String getTemp();

    String getTime();

    String getPressure();

    String getWind();

    String getHumidity();

    Uri getWeatherImageUrl();
}
