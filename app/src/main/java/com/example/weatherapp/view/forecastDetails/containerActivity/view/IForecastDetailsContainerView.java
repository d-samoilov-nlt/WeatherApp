package com.example.weatherapp.view.forecastDetails.containerActivity.view;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public interface IForecastDetailsContainerView {
    void showForecastDetails(ICityLocation cityLocation);

    void updateForecastDetails(ICityLocation cityLocation);
    void showShortForecastDetails(IForecastShortDetailsDisplayModel dm);
}
