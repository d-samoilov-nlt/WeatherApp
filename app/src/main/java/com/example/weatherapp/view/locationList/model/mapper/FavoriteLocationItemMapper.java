package com.example.weatherapp.view.locationList.model.mapper;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.util.UtilWeatherDisplayFormat;
import com.example.weatherapp.util.UtilWeatherIcon;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.ForecastShortDetailsDisplayModel;
import com.example.weatherapp.view.locationList.model.FavoriteLocationItemDisplayModel;
import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteLocationItemMapper implements IFavoriteLocationItemMapper {

    @Override
    public List<IFavoriteLocationItemDisplayModel> map(List<IFavoriteLocationCacheData> cacheDataList) {
        List<IFavoriteLocationItemDisplayModel> displayModels = new ArrayList<>();

        for (IFavoriteLocationCacheData cacheData : cacheDataList) {
            IFavoriteLocationItemDisplayModel displayModel =
                    new FavoriteLocationItemDisplayModel(
                            UtilWeatherIcon.getOpenWeatherIconUrl(cacheData.getCurrentWeather().getWeatherList()[0].getIconName()),
                            new ForecastShortDetailsDisplayModel(
                                    cacheData.getCityName(),
                                    UtilWeatherDisplayFormat.formatToDisplayValueTemp(cacheData.getCurrentWeather().getMain().getTemp()),
                                    cacheData.getCurrentWeather().getWeatherList()[0].getDescription(),
                                    cacheData.getForecastUnitType()
                            )
                    );


            displayModels.add(displayModel);
        }

        return displayModels;
    }
}
