package com.example.weatherapp.domain.mapper;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapp.data.model.forecast.shortDetails.ForecastShortDetailsDisplayModel;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;
import com.example.weatherapp.util.UtilWeatherDisplayFormat;

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
