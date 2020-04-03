package com.example.weatherapi.data.entity.interfaces.currentWeather;

public interface IMainResponse {

    double getTemp();

    int getPressure();

    int getHumidity();

    double getTempMin();

    double getTempMax();
}
