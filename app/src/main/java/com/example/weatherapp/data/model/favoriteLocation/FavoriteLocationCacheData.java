package com.example.weatherapp.data.model.favoriteLocation;

import com.example.weatherapi.data.entity.interfaces.currentWeather.ICurrentWeatherResponse;
import com.example.weatherapi.data.entity.interfaces.severalDaysWeather.ISeveralDaysWeatherResponse;

public class FavoriteLocationCacheData implements IFavoriteLocationCacheData {
    private final int utilType;
    private final String cityName;
    private final ICurrentWeatherResponse currentWeatherResponse;
    private final ISeveralDaysWeatherResponse severalDaysWeatherResponse;

    public FavoriteLocationCacheData(
            int utilType,
            String cityName,
            ICurrentWeatherResponse currentWeatherResponse,
            ISeveralDaysWeatherResponse severalDaysWeatherResponse) {

        this.utilType = utilType;
        this.cityName = cityName;
        this.currentWeatherResponse = currentWeatherResponse;
        this.severalDaysWeatherResponse = severalDaysWeatherResponse;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public int getUtilType() {
        return utilType;
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
