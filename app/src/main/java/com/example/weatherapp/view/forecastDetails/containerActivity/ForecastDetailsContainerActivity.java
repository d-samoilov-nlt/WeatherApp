package com.example.weatherapp.view.forecastDetails.containerActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.AsyncForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.ForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.presenter.IForecastDetailsContainerPresenter;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.ForecastDetailsContainerView;
import com.example.weatherapp.view.forecastDetails.containerActivity.view.InMainForecastDetailsContainerView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.ShowForecastDetailsByLocationUseCase;

public class ForecastDetailsContainerActivity extends WeatherAppActivity {
    private IForecastDetailsContainerPresenter presenter;

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
                        new InMainForecastDetailsContainerView(
                                new ForecastDetailsContainerView(findViewById(R.id.cl_forecast_details_container))
                        ),
                        new GetCurrentWeatherByCityNameUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                        new ForecastShortDetailsMapper(),
                        new ShowForecastDetailsByLocationUseCase(
                                getSupportFragmentManager(),
                                R.id.fl_forecast_details
                        )));

        presenter.onCreate();
    }

    private void setupView() {
        ivRefresh = findViewById(R.id.iv_forecast_details_container_toolbar_refresh);
    }

    private void setupActions() {
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }


    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_forecast_details_container);
    }
}
