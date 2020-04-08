package com.example.weatherapp.forecastDetails.containerActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.domain.useCase.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.common.view.WeatherAppActivity;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.deviceLocation.SerializableDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.forecastDetails.containerActivity.presenter.AsyncForecastDetailsContainerPresenter;
import com.example.weatherapp.forecastDetails.containerActivity.presenter.ForecastDetailsContainerPresenter;
import com.example.weatherapp.forecastDetails.containerActivity.presenter.IForecastDetailsContainerPresenter;
import com.example.weatherapp.forecastDetails.containerActivity.view.IForecastDetailsContainerView;
import com.example.weatherapp.forecastDetails.containerActivity.view.InMainForecastDetailsContainerView;
import com.example.weatherapp.forecastDetails.fragment.ForecastDetailsFragment;
import com.example.weatherapp.provider.OpenWeatherApiProvider;

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
    public void showForecastDetails(IDeviceLocation deviceLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.DEVICE_LOCATION_KEY,
                new SerializableDeviceLocation(deviceLocation));

        ForecastDetailsFragment forecastDetailsFragment = new ForecastDetailsFragment();
        forecastDetailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_forecast_details, forecastDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void updateForecastDetails(IDeviceLocation deviceLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.DEVICE_LOCATION_KEY,
                new SerializableDeviceLocation(deviceLocation));

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
