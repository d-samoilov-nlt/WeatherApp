package com.example.weatherapp.data.model.favoriteLocation;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;

public class FavoriteLocationCacheData implements IFavoriteLocationCacheData {
    private final int unitType;
    private final String cityName;
    private final ICurrentWeatherResponse currentWeatherResponse;
    private final ISeveralDaysWeatherResponse severalDaysWeatherResponse;

    public FavoriteLocationCacheData(
            int unitType,
            String cityName,
            ICurrentWeatherResponse currentWeatherResponse,
            ISeveralDaysWeatherResponse severalDaysWeatherResponse) {

        this.unitType = unitType;
        this.cityName = cityName;
        this.currentWeatherResponse = currentWeatherResponse;
        this.severalDaysWeatherResponse = severalDaysWeatherResponse;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public int getForecastUnitType() {
        return unitType;
    }

    @Override
    public ICurrentWeatherResponse getCurrentWeather() {
        return currentWeatherResponse;
    }

    @Override
    public ISeveralDaysWeatherResponse getSeveralDaysForecast() {
        return severalDaysWeatherResponse;
    }
}
