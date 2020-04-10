package com.example.weatherapp.view.favoriteLocationForecastDetails;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.AsyncFavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.FavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.IFavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.FavoriteLocationForecastDetailsView;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.InMainThreadFavoriteLocationForecastDetailsView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.ShowForecastDetailsByLocationUseCase;

public class FavoriteLocationForecastDetailsActivity extends WeatherAppActivity {
    private IFavoriteLocationForecastDetailsPresenter presenter;

    private ImageView ivFavoriteLocation;
    private ImageView ivRefresh;

    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_favorite_location_forecast_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_location_forecast_details);
        setupView();
        setupActions();

        presenter =
                new AsyncFavoriteLocationForecastDetailsPresenter(
                        new FavoriteLocationForecastDetailsPresenter(
                                new InMainThreadFavoriteLocationForecastDetailsView(
                                        new FavoriteLocationForecastDetailsView(
                                                findViewById(R.id.cl_favorite_location_forecast_container)
                                        )),
                                getIntent().getStringExtra(FavoriteLocationForecastDetailsConst.CITY_NAME_KEY),
                                FavoriteLocationRepositoryProvider.get(getApplicationContext()),
                                new GetCurrentWeatherByCityNameUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                                new GetSeveralDaysForecastUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                                new ForecastShortDetailsMapper(),
                                new ShowForecastDetailsByLocationUseCase(
                                        getSupportFragmentManager(),
                                        R.id.fl_favorite_location_forecast_details)));

        presenter.onCreate();

    }

    private void setupView() {
        ivFavoriteLocation = findViewById(R.id.iv_favorite_location_forecast_toolbar_favorite);
        ivRefresh = findViewById(R.id.iv_favorite_location_forecast_toolbar_update);
    }

    private void setupActions() {
        ivFavoriteLocation.setOnClickListener(v -> presenter.onFavoriteBtnPressed());
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }
}
