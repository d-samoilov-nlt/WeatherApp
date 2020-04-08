package com.example.weatherapp.view.favoriteLocationForecastDetails;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.domain.useCase.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.AsyncFavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.FavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.presenter.IFavoriteLocationForecastDetailsPresenter;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.IFavoriteLocationForecastDetailsView;
import com.example.weatherapp.view.favoriteLocationForecastDetails.view.InMainThreadFavoriteLocationForecastDetailsView;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.fragment.ForecastDetailsFragment;

import java.util.concurrent.Executors;

public class FavoriteLocationForecastDetailsActivity extends WeatherAppActivity implements IFavoriteLocationForecastDetailsView {
    private IFavoriteLocationForecastDetailsPresenter presenter;

    private ImageView ivFavoriteLocation;
    private ImageView ivRefresh;
    private TextView tvForecastShortDetails;
    private FrameLayout flForecastDetailsContainer;
    private ConstraintLayout clForecastLoadingProgressContainer;

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
                                new InMainThreadFavoriteLocationForecastDetailsView(this),
                                getIntent().getStringExtra(FavoriteLocationForecastDetailsConst.CITY_NAME_KEY),
                                FavoriteLocationRepositoryProvider.get(getApplicationContext()),
                                new GetCurrentWeatherByCityNameUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                                new GetSeveralDaysForecastUseCase(OpenWeatherApiProvider.get(getApplicationContext())),
                                new ForecastShortDetailsMapper()),
                        Executors.newCachedThreadPool());

        presenter.onCreate();

    }

    private void setupView() {
        tvForecastShortDetails = findViewById(R.id.tv_favorite_location_forecast_toolbar_short_details);
        ivFavoriteLocation = findViewById(R.id.iv_favorite_location_forecast_toolbar_favorite);
        ivRefresh = findViewById(R.id.iv_favorite_location_forecast_toolbar_update);
        flForecastDetailsContainer = findViewById(R.id.fl_favorite_location_forecast_details);
        clForecastLoadingProgressContainer = findViewById(R.id.cl_favorite_location_forecast_details_loading_progress);
    }

    private void setupActions() {
        ivFavoriteLocation.setOnClickListener(v -> presenter.onFavoriteBtnPressed());
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }

    @Override
    public void showForecastDetails(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        ForecastDetailsFragment detailsFragment = new ForecastDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_favorite_location_forecast_details, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        tvForecastShortDetails.setText(
                String.format(
                        getResources().getString(R.string.current_location_short_data_far),
                        dm.getCityName(),
                        dm.getTemp(),
                        dm.getForecast()));
    }

    @Override
    public void setIsFavoriteSelected(boolean isSelected) {
        if (isSelected) {
            ivFavoriteLocation.setImageResource(R.drawable.ic_favorite_white_24dp);
        } else {
            ivFavoriteLocation.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }
    }

    @Override
    public void setLoadingProcess(boolean isLoading) {
        if (isLoading) {
            clForecastLoadingProgressContainer.setVisibility(View.VISIBLE);
            flForecastDetailsContainer.setVisibility(View.GONE);
        } else {
            clForecastLoadingProgressContainer.setVisibility(View.GONE);
            flForecastDetailsContainer.setVisibility(View.VISIBLE);
        }
    }
}
