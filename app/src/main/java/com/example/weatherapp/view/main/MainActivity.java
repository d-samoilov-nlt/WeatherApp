package com.example.weatherapp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.main.presenter.AsyncMainPresenter;
import com.example.weatherapp.view.main.presenter.IMainPresenter;
import com.example.weatherapp.view.main.presenter.MainPresenter;
import com.example.weatherapp.view.main.presenter.SafeMainPresenter;
import com.example.weatherapp.view.main.view.InMainThreadMainView;
import com.example.weatherapp.view.main.view.MainView;
import com.example.weatherapp.view.searchCity.SearchCityActivity;

public class MainActivity extends WeatherAppActivity {
    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActions();

        presenter =
                new AsyncMainPresenter(
                        new SafeMainPresenter(
                                new MainPresenter(
                                        () -> startActivity(new Intent(MainActivity.this, SearchCityActivity.class)),
                                        new InMainThreadMainView(
                                                new MainView(
                                                        findViewById(R.id.cl_main_container),
                                                        getSupportFragmentManager())))));

        presenter.onCreate();

    }

    private void setupActions() {
        findViewById(R.id.fab_main_check_weather_for_city).setOnClickListener(v -> presenter.onSearchByCityPressed());
    }

    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_main_container);
    }
}
