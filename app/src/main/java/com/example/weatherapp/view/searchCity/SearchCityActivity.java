package com.example.weatherapp.view.searchCity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.GetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByName.GetCurrentWeatherByCityNameUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.service.DeviceLocationService;
import com.example.weatherapp.view.common.DelayedAfterTextChangedWatcher;
import com.example.weatherapp.view.common.LocationEditText;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecastDetailsActivity;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.containerActivity.ForecastDetailsContainerActivity;
import com.example.weatherapp.view.searchCity.presenter.AsyncSearchCityPresenter;
import com.example.weatherapp.view.searchCity.presenter.ISearchCityPresenter;
import com.example.weatherapp.view.searchCity.presenter.SearchCityPresenter;
import com.example.weatherapp.view.searchCity.view.ISearchCityView;
import com.example.weatherapp.view.searchCity.view.InMainThreadSearchCityView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import static com.example.weatherapp.util.FocusUtils.requestFocusWithSoftKeyboard;

public class SearchCityActivity extends WeatherAppActivity implements ISearchCityView, DeviceLocationService.OnLocationUpdateListener {
    private Intent deviceLocationServiceIntent;
    private DeviceLocationService deviceLocationService;

    private ISearchCityPresenter presenter;

    private LocationEditText etEnterLocationData;
    private Switch switchUnitType;
    private Switch switchIsFavorite;
    private Button btnViewWeather;

    private Boolean isServiceBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        setupView();
        setupActions();
        deviceLocationServiceIntent = new Intent(this, DeviceLocationService.class);
        isServiceBind = false;

        presenter =
                new AsyncSearchCityPresenter(
                        new SearchCityPresenter(
                                new InMainThreadSearchCityView(
                                        this
                                ),
                                new GetCurrentWeatherByCityLocationUseCase(
                                        OpenWeatherApiProvider.get(getApplicationContext())
                                ),
                                new GetCurrentWeatherByCityNameUseCase(
                                        OpenWeatherApiProvider.get(getApplicationContext())
                                ),
                                new GetSeveralDaysForecastUseCase(
                                        OpenWeatherApiProvider.get(getApplicationContext())
                                ),
                                FavoriteLocationRepositoryProvider.get(getApplicationContext()),
                                cityName -> {
                                    Intent intent =
                                            new Intent(SearchCityActivity.this, FavoriteLocationForecastDetailsActivity.class);
                                    intent.putExtra(FavoriteLocationForecastDetailsConst.CITY_NAME_KEY, cityName);
                                    SearchCityActivity.this.startActivity(intent);
                                },
                                cityName -> {
                                    Intent intent =
                                            new Intent(SearchCityActivity.this, ForecastDetailsContainerActivity.class);
                                    intent.putExtra(ForecastDetailsConst.CITY_NAME_KEY, cityName);
                                    SearchCityActivity.this.startActivity(intent);
                                }));
    }

    private void setupView() {
        etEnterLocationData = findViewById(R.id.et_search_city_location);
        switchUnitType = findViewById(R.id.switch_search_city_unit_type);
        switchIsFavorite = findViewById(R.id.switch_search_city_favorite);
        btnViewWeather = findViewById(R.id.btn_search_city_view_weather);

        requestFocusWithSoftKeyboard(etEnterLocationData);
    }

    private void setupActions() {
        etEnterLocationData.addTextChangedListener(
                new DelayedAfterTextChangedWatcher(
                        new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                // nop
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                // nop
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                presenter.onLocationEntered(s.toString());
                            }
                        }
                )
        );

        etEnterLocationData.setOnLocationIconClickListener(isEnabled -> {
            presenter.onLocationIconPressed(isEnabled);
        });

        btnViewWeather.setOnClickListener(v -> presenter.onViewWeatherPressed());
        switchIsFavorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            presenter.onFavoriteSelected(isChecked);
        });
        switchUnitType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            presenter.onUnitTypeSelected(isChecked);
        });

    }

    @Override
    public void setViewWeatherBtnEnabled(boolean isEnabled) {
        btnViewWeather.setEnabled(isEnabled);
    }

    @Override
    public void setDeviceLocationInfo(String locationInfo) {
        etEnterLocationData.setText(locationInfo);
    }

    @Override
    public void showCityNotFoundError(boolean isError) {
        if (isError) {
            etEnterLocationData.setError(getResources().getString(R.string.error_city_not_found));
        } else {
            etEnterLocationData.setError(null);
        }
    }

    @Override
    public void stopLocationService() {
        if (!isServiceBind) {
            return;
        }
        isServiceBind = true;
        if (deviceLocationService != null) {
            deviceLocationService.removeListener();
        }
        unbindService(this);
        stopService(deviceLocationServiceIntent);
    }

    @Override
    public void setLocationIconEnabled(boolean isEnabled) {
        etEnterLocationData.setLocationIconEnabled(isEnabled);
    }

    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_search_city_container);
    }

    @Override
    public void startLocationService() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            if (!isServiceBind) {
                                startService(deviceLocationServiceIntent);
                                bindService(deviceLocationServiceIntent, SearchCityActivity.this, Context.BIND_AUTO_CREATE);
                            }
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .check();
    }

    @Override
    public void onUpdated(IDeviceLocation deviceLocation) {
        presenter.onLocationUpdated(deviceLocation);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        DeviceLocationService.LocalBinder localBinder = (DeviceLocationService.LocalBinder) service;
        deviceLocationService = localBinder.getServiceInstance();
        deviceLocationService.attachListener(this);
        isServiceBind = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isServiceBind = false;
    }
}
