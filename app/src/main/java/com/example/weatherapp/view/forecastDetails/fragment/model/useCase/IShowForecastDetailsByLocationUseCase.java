package com.example.weatherapp.view.forecastDetails.fragment.model.useCase;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;

public interface IShowForecastDetailsByLocationUseCase {
    void show(ICityLocation cityLocation);

    void show(ICityLocation cityLocation, int unitType);

}
