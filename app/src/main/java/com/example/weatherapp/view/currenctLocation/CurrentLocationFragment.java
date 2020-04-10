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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.data.entity.interfaces.cityLocation.ICityLocation;
import com.example.weatherapi.domain.useCase.getCurrentWeatherByLocation.GetCurrentWeatherByCityLocationUseCase;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.data.model.deviceLocation.IDeviceLocation;
import com.example.weatherapp.data.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;
import com.example.weatherapp.domain.mapper.ForecastShortDetailsMapper;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.LastDeviceLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.service.DeviceLocationService;
import com.example.weatherapp.view.currenctLocation.presenter.AsyncCurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.CurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.ICurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.presenter.SafeCurrentLocationPresenter;
import com.example.weatherapp.view.currenctLocation.view.ICurrentLocationView;
import com.example.weatherapp.view.currenctLocation.view.InMainThreadCurrentLocationView;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.fragment.ForecastDetailsFragment;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;
import java.util.concurrent.Executors;

public class CurrentLocationFragment extends Fragment implements DeviceLocationService.OnLocationUpdateListener, ICurrentLocationView {
    private View view;
    private Context context;

    private Intent deviceLocationServiceIntent;
    private DeviceLocationService deviceLocationService;
    private ICurrentLocationPresenter presenter;

    private TextView tvSearchingProgress;
    private TextView tvSearchingError;
    private TextView tvToolbarShortDetails;

    private ImageView ivFavoriteLocation;
    private ImageView ivRefresh;

    private Button btnTryAgain;
    private ProgressBar pbSearchingProgress;

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
                                                this
                                        ),
                                        new GetCurrentWeatherByCityLocationUseCase(
                                                OpenWeatherApiProvider.get(context.getApplicationContext())
                                        ),
                                        new ForecastShortDetailsMapper(),
                                        LastDeviceLocationRepositoryProvider.get(context.getApplicationContext()),
                                        FavoriteLocationRepositoryProvider.get(context.getApplicationContext()),
                                        new GetSeveralDaysForecastUseCase(
                                                OpenWeatherApiProvider.get(context.getApplicationContext())))),
                        Executors.newCachedThreadPool()
                );

        presenter.onCreate();

        return view;
    }

    private void setupService() {
        deviceLocationServiceIntent = new Intent(view.getContext(), DeviceLocationService.class);
        isServiceBound = false;
    }

    private void setupView() {
        tvSearchingProgress = view.findViewById(R.id.tv_current_location_searching_progress);
        pbSearchingProgress = view.findViewById(R.id.pb_current_location_searching_progress);

        tvSearchingError = view.findViewById(R.id.tv_current_location_error_msg);
        btnTryAgain = view.findViewById(R.id.btn_fragment_current_location_try_again);

        tvToolbarShortDetails = view.findViewById(R.id.tv_current_location_toolbar_short_details);

        ivFavoriteLocation = view.findViewById(R.id.iv_current_location_toolbar_favorite);
        ivRefresh = view.findViewById(R.id.iv_current_location_toolbar_update);

        btnTryAgain.setOnClickListener(v -> presenter.onTrySearchAgainPressed());
        ivFavoriteLocation.setOnClickListener(v -> presenter.onAddToFavoritePressed());
        ivRefresh.setOnClickListener(v -> presenter.onRefreshPressed());
    }


    @Override
    public void setIsSearchingLocationProcess(boolean isProcess) {
        int visibility = isProcess ? View.VISIBLE : View.GONE;

        pbSearchingProgress.setVisibility(visibility);
        tvSearchingProgress.setVisibility(visibility);
    }

    @Override
    public void setIsPermissionRequiredError(boolean isError) {
        int visibility = isError ? View.VISIBLE : View.GONE;

        tvSearchingError.setVisibility(visibility);
        btnTryAgain.setVisibility(visibility);
    }

    @Override
    public void showShortForecastDetails(IForecastShortDetailsDisplayModel dm) {
        int stringResId;

        if (dm.getForecastUnitType() == ForecastUnitsType.CELSIUS.getValue()) {
            stringResId = R.string.current_location_short_data_cel;

        } else if (dm.getForecastUnitType() == ForecastUnitsType.FAHRENHEIT.getValue()) {
            stringResId = R.string.current_location_short_data_far;
        } else {
            throw new IllegalStateException("Unsupported ForecastUnitsType - " + dm.getForecastUnitType());
        }

        tvToolbarShortDetails.setText(
                String.format(
                        getResources().getString(stringResId),
                        dm.getCityName(),
                        dm.getTemp(),
                        dm.getForecast()));
    }

    @Override
    public void showForecastDetails(ICityLocation cityLocation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(
                ForecastDetailsConst.CITY_LOCATION_KEY,
                new SerializableCityLocation(cityLocation));

        ForecastDetailsFragment detailsFragment = new ForecastDetailsFragment();
        detailsFragment.setArguments(bundle);

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_forecast_details, detailsFragment);
        transaction.commit();
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
    public void stopLocationService() {
        if (isServiceBound) {
            if (deviceLocationService != null) {
                deviceLocationService.removeListener();
            }
            context.unbindService(CurrentLocationFragment.this);
            context.stopService(deviceLocationServiceIntent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    @Override
    public void startLocationService() {
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
