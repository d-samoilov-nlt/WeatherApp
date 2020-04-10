package com.example.weatherapp.view.forecastDetails.fragment.model.useCase;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.fragment.ForecastDetailsFragment;

public class ShowForecastDetailsByLocationUseCase implements IShowForecastDetailsByLocationUseCase {
    private final FragmentManager fragmentManager;

    public ShowForecastDetailsByLocationUseCase(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void show(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        ForecastDetailsFragment detailsFragment = new ForecastDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_forecast_details, detailsFragment);
        transaction.commit();
    }
}
