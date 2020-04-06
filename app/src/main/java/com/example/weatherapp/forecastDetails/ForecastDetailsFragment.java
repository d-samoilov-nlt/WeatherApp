package com.example.weatherapp.forecastDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.common.view.ForecastDetailsToolbarButton;
import com.example.weatherapp.data.deviceLocation.SerializableDeviceLocation;
import com.example.weatherapp.forecastDetails.model.mapper.TodayForecastMapper;
import com.example.weatherapp.forecastDetails.presenter.AsyncForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.presenter.ForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.presenter.IForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.view.ForecastDetailsRVAdapter;
import com.example.weatherapp.forecastDetails.view.ForecastDetailsView;
import com.example.weatherapp.forecastDetails.view.InMainThreadForecastDetailsView;
import com.example.weatherapp.provider.OpenWeatherApiProvider;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class ForecastDetailsFragment extends Fragment {
    private IForecastDetailsPresenter presenter;

    private View view;
    private ForecastDetailsRVAdapter forecastDetailsRVAdapter;

    private ForecastDetailsToolbarButton btnToday;
    private ForecastDetailsToolbarButton btnTomorrow;
    private ForecastDetailsToolbarButton btnSeveralDays;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forecast_details, container, false);

        setupView();
        setupActions();

        presenter =
                new AsyncForecastDetailsPresenter(
                        new ForecastDetailsPresenter(
                                new InMainThreadForecastDetailsView(
                                        new ForecastDetailsView(
                                                forecastDetailsRVAdapter
                                        )
                                ),
                                new GetSeveralDaysForecastUseCase(OpenWeatherApiProvider.get()),
                                new TodayForecastMapper()
                        ),
                        Executors.newCachedThreadPool()
                );

        presenter.onCreate((SerializableDeviceLocation) getArguments().getSerializable(ForecastDetailsBundleKeys.DEVICE_LOCATION_KEY));

        return view;
    }

    private void setupActions() {
        btnToday.setSelected(true);
    }

    private void setupView() {
        btnToday = view.findViewById(R.id.btn_forecast_details_toolbar_today);
        btnTomorrow = view.findViewById(R.id.btn_forecast_details_toolbar_tomorrow);
        btnSeveralDays = view.findViewById(R.id.btn_forecast_details_toolbar_several_days);

        setupRv();
    }

    private void setupRv() {
        RecyclerView rvForecast = view.findViewById(R.id.rv_forecast_details_forecast);
        rvForecast.setLayoutManager(
                new LinearLayoutManager(
                        view.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        forecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());

        rvForecast.setAdapter(forecastDetailsRVAdapter);
    }
}
