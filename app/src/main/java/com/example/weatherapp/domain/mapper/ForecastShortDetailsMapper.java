package com.example.weatherapp.domain.mapper;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapp.util.UtilWeatherDisplayFormat;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.ForecastShortDetailsDisplayModel;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class ForecastShortDetailsMapper implements IForecastShortDetailsMapper {

    @Override
    public IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response) {
        return map(response, ForecastUnitsType.CELSIUS.getValue());
    }

    @Override
    public IForecastShortDetailsDisplayModel map(ICurrentWeatherResponse response, int unitType) {
        return
                new ForecastShortDetailsDisplayModel(
                        response.getCityName(),
                        UtilWeatherDisplayFormat.formatToDisplayValueTemp(response.getMain().getTemp()),
                        response.getWeatherList()[0].getDescription(),
                        unitType);
    }
}
