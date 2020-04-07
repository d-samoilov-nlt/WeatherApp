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
import com.example.weatherapp.data.model.deviceLocation.SerializableDeviceLocation;
import com.example.weatherapp.forecastDetails.model.mapper.SeveralDaysForecastMapper;
import com.example.weatherapp.forecastDetails.model.mapper.TodayForecastMapper;
import com.example.weatherapp.forecastDetails.model.mapper.TomorrowForecastMapper;
import com.example.weatherapp.forecastDetails.presenter.AsyncForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.presenter.ForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.presenter.IForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.presenter.SafeForecastDetailsPresenter;
import com.example.weatherapp.forecastDetails.view.ForecastDetailsRVAdapter;
import com.example.weatherapp.forecastDetails.view.ForecastDetailsView;
import com.example.weatherapp.forecastDetails.view.ForecastSeveralDaysDetailsRvAdapter;
import com.example.weatherapp.forecastDetails.view.InMainThreadForecastDetailsView;
import com.example.weatherapp.provider.OpenWeatherApiProvider;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class ForecastDetailsFragment extends Fragment implements View.OnClickListener {
    private IForecastDetailsPresenter presenter;

    private View view;

    private RecyclerView rvForecast;
    private ForecastDetailsRVAdapter todayForecastDetailsRVAdapter;
    private ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter;
    private ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter;

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
                        new SafeForecastDetailsPresenter(
                                new ForecastDetailsPresenter(
                                        new InMainThreadForecastDetailsView(
                                                new ForecastDetailsView(
                                                        todayForecastDetailsRVAdapter,
                                                        tomorrowForecastDetailsRVAdapter,
                                                        forecastSeveralDaysDetailsRvAdapter,
                                                        view.findViewById(R.id.cl_forecast_details_content),
                                                        view.findViewById(R.id.cl_forecast_loading_progress),
                                                        view.findViewById(R.id.cl_forecast_error_message))
                                        ),
                                        new GetSeveralDaysForecastUseCase(OpenWeatherApiProvider.get(getContext().getApplicationContext())),
                                        new TodayForecastMapper(getResources()),
                                        new TomorrowForecastMapper(getResources()),
                                        new SeveralDaysForecastMapper(getResources()))
                        ),
                        Executors.newCachedThreadPool()
                );

        presenter.onCreate((SerializableDeviceLocation) getArguments().getSerializable(ForecastDetailsBundleKeys.DEVICE_LOCATION_KEY));

        return view;
    }

    private void setupActions() {
        btnToday.setIsSelectedBackground(true);
    }

    private void setupView() {
        btnToday = view.findViewById(R.id.btn_forecast_details_toolbar_today);
        btnTomorrow = view.findViewById(R.id.btn_forecast_details_toolbar_tomorrow);
        btnSeveralDays = view.findViewById(R.id.btn_forecast_details_toolbar_several_days);

        btnToday.setOnClickListener(this);
        btnTomorrow.setOnClickListener(this);
        btnSeveralDays.setOnClickListener(this);

        // TODO : refactor to use group
        btnToday.setIsSelectedBackground(true);
        btnTomorrow.setIsSelectedBackground(false);
        btnSeveralDays.setIsSelectedBackground(false);

        setupRv();

        rvForecast.setAdapter(todayForecastDetailsRVAdapter);
    }

    private void setupRv() {
        rvForecast = view.findViewById(R.id.rv_forecast_details_forecast);
        rvForecast.setLayoutManager(
                new LinearLayoutManager(
                        view.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        todayForecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());
        tomorrowForecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());
        forecastSeveralDaysDetailsRvAdapter = new ForecastSeveralDaysDetailsRvAdapter(new ArrayList<>());
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == btnToday.getId()) {
            btnToday.setIsSelectedBackground(true);
            btnTomorrow.setIsSelectedBackground(false);
            btnSeveralDays.setIsSelectedBackground(false);

            rvForecast.setAdapter(todayForecastDetailsRVAdapter);

            presenter.onTodayPressed();

        } else if (viewId == btnTomorrow.getId()) {
            btnToday.setIsSelectedBackground(false);
            btnTomorrow.setIsSelectedBackground(true);
            btnSeveralDays.setIsSelectedBackground(false);

            rvForecast.setAdapter(tomorrowForecastDetailsRVAdapter);

            presenter.onTomorrowPressed();

        } else if (viewId == btnSeveralDays.getId()) {
            btnToday.setIsSelectedBackground(false);
            btnTomorrow.setIsSelectedBackground(false);
            btnSeveralDays.setIsSelectedBackground(true);

            rvForecast.setAdapter(forecastSeveralDaysDetailsRvAdapter);

            presenter.onFiveDaysPressed();
        }
    }
}
