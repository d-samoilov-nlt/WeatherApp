package com.example.weatherapp.view.forecastDetails.fragment.model.useCase;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.fragment.ForecastDetailsFragment;

public class ShowForecastDetailsByLocationUseCase implements IShowForecastDetailsByLocationUseCase {
    private final FragmentManager fragmentManager;
    private final int fragmentContainerViewId;

    public ShowForecastDetailsByLocationUseCase(
            FragmentManager fragmentManager,
            int fragmentContainerViewId) {

        this.fragmentManager = fragmentManager;
        this.fragmentContainerViewId = fragmentContainerViewId;
    }

    @Override
    public void show(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        showDetailsFragment(bundle);
    }

    @Override
    public void show(ICityLocation cityLocation, int unitType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));
        bundle.putInt(
                ForecastDetailsConst.UNIT_TYPE_KEY,
                unitType);

        showDetailsFragment(bundle);
    }

    private void showDetailsFragment(Bundle bundle) {
        ForecastDetailsFragment detailsFragment = new ForecastDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentContainerViewId, detailsFragment);
        transaction.commit();
    }
}
