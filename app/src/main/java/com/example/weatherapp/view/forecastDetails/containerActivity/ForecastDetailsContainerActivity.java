package com.example.weatherapp.view.forecastDetails.containerActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.AsyncForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.ForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.IForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.IForecastDetailsContainerView;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.InMainForecastDetailsContainerView;
import com.example.weatherapp.view.forecastDetails.fragment.ForecastDetailsFragment;

import java.util.concurrent.Executors;

public class ForecastDetailsContainerActivity extends WeatherAppActivity implements IForecastDetailsContainerView {
    private IForecastDetailsContainerPresenter presenter;

    private TextView tvShortForecastDetails;
    private ImageView ivRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_details_container);
        setupView();
        setupActions();

        presenter = new AsyncForecastDetailsContainerPresenter(
                new ForecastDetailsContainerPresenter(
                        getIntent().getStringExtra(ForecastDetailsConst.CITY_NAME_KEY),
                        new InMainForecastDetailsContainerView(this),
                        new GetCurrentWeatherByCityNameUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                        new ForecastShortDetailsMapper()
                ),
                Executors.newCachedThreadPool()
        );

        presenter.onCreate();
    }

    private void setupView() {
        tvShortForecastDetails = findViewById(R.id.tv_forecast_details_container_toolbar_short_details);
        ivRefresh = findViewById(R.id.iv_forecast_details_container_toolbar_refresh);
    }

    private void setupActions() {
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }


    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_forecast_details_container);
    }

    @Override
    public void showForecastDetails(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        ForecastDetailsFragment forecastDetailsFragment = new ForecastDetailsFragment();
        forecastDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_forecast_details, forecastDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void updateForecastDetails(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        ForecastDetailsFragment forecastDetailsFragment = new ForecastDetailsFragment();
        forecastDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_forecast_details, forecastDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        tvShortForecastDetails.setText(
                String.format(
                        getResources().getString(R.string.current_location_short_data_far),
                        dm.getCityName(),
                        dm.getTemp(),
                        dm.getForecast()));
    }
}
