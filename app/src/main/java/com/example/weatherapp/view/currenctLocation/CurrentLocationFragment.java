package com.example.weatherapp.view.currenctLocation;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.GetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.LastDeviceLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.service.DeviceLocationService;
import com.example.weatherapp.service.ILocationService;
import com.example.weatherapp.view.currenctLocation.presenter.AsyncCurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.CurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.ICurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.SafeCurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.view.CurrentLocationView;
import com.example.weatherapp.view.currenctLocation.view.InMainThreadCurrentLocationView;
import com.example.weatherapp.view.forecastDetails.fragment.model.useCase.ShowForecastDetailsByLocationUseCase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class CurrentLocationFragment extends Fragment implements DeviceLocationService.OnLocationUpdateListener {
    private View view;
    private Context context;

    private Intent deviceLocationServiceIntent;
    private DeviceLocationService deviceLocationService;
    private ICurrentLocationPresenter presenter;

    private Boolean isServiceBound;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_location, container, false);
        context = view.getContext();

        setupService();
        setupView();

        presenter =
                new AsyncCurrentLocationPresenter(
                        new SafeCurrentLocationPresenter(
                                new CurrentLocationPresenter(
                                        new InMainThreadCurrentLocationView(
                                                new CurrentLocationView(view)
                                        ),
                                        new GetCurrentWeatherByCityLocationUseCase(
                                                OpenWeatherApiProvider.get(context.getApplicationContext())
                                        ),
                                        new ForecastShortDetailsMapper(),
                                        LastDeviceLocationRepositoryProvider.get(context.getApplicationContext()),
                                        FavoriteLocationRepositoryProvider.get(context.getApplicationContext()),
                                        new GetSeveralDaysForecastUseCase(
                                                OpenWeatherApiProvider.get(context.getApplicationContext())),
                                        new ILocationService() {
                                            @Override
                                            public void stopService() {
                                                stopLocationService();
                                            }

                                            @Override
                                            public void startService() {
                                                startLocationService();
                                            }
                                        },
                                        new ShowForecastDetailsByLocationUseCase(
                                                getChildFragmentManager(),
                                                R.id.fl_forecast_details))));

        presenter.onCreate();

        return view;
    }

    private void setupService() {
        deviceLocationServiceIntent = new Intent(view.getContext(), DeviceLocationService.class);
        isServiceBound = false;
    }

    private void setupView() {

        Button btnTryAgain = view.findViewById(R.id.btn_fragment_current_location_try_again);
        ImageView ivFavoriteLocation = view.findViewById(R.id.iv_current_location_toolbar_favorite);
        ImageView ivRefresh = view.findViewById(R.id.iv_current_location_toolbar_update);

        btnTryAgain.setOnClickListener(v -> presenter.onTrySearchAgainPressed());
        ivFavoriteLocation.setOnClickListener(v -> presenter.onAddToFavoritePressed());
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    private void stopLocationService() {
        if (isServiceBound) {
            isServiceBound = false;
            if (deviceLocationService != null) {
                deviceLocationService.removeListener();
            }
            context.unbindService(CurrentLocationFragment.this);
            context.stopService(deviceLocationServiceIntent);
        }
    }

    private void startLocationService() {
        Dexter.withActivity(requireActivity())
                .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            presenter.onPermissionGranted();

                            if (!isServiceBound) {
                                context.startService(deviceLocationServiceIntent);
                                context.bindService(
                                        deviceLocationServiceIntent,
                                        CurrentLocationFragment.this,
                                        Context.BIND_AUTO_CREATE);
                            }

                        } else {
                            presenter.onPermissionDenied();
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
        deviceLocationService.attachListener(CurrentLocationFragment.this);
        isServiceBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        isServiceBound = false;
    }
}
