package com.example.weatherapp.domain.mapper;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapp.data.model.forecast.shortDetails.ForecastShortDetailsDisplayModel;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class ForecastShortDetailsMapper implements IForecastShortDetailsMapper {

    @Override
    public IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response) {
        return new ForecastShortDetailsDisplayModel(
                response.getCityName(),
                String.valueOf((int) response.getMain().getTemp()),
                response.getWeatherList()[0].getDescription()
        );
    }
}
