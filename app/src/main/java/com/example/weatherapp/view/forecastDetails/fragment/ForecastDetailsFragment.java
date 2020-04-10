package com.example.weatherapp.view.forecastDetails.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapi.domain.useCase.getSeveralDaysForecast.GetSeveralDaysForecastUseCase;
import com.example.weatherapp.R;
import com.example.weatherapp.data.model.cityLocation.SerializableCityLocation;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.provider.OpenWeatherApiProvider;
import com.example.weatherapp.view.common.forecastDetailsToolbar.ForecastDetailsToolbarButton;
import com.example.weatherapp.view.common.forecastDetailsToolbar.ForecastDetailsToolbarViewGroup;
import com.example.weatherapp.view.forecastDetails.ForecastDetailsConst;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.SeveralDaysForecastMapper;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.TodayForecastMapper;
import com.example.weatherapp.view.forecastDetails.fragment.model.mapper.TomorrowForecastMapper;
import com.example.weatherapp.view.forecastDetails.fragment.presenter.AsyncForecastDetailsPresenter;
import com.example.weatherapp.view.forecastDetails.fragment.presenter.ForecastDetailsPresenter;
import com.example.weatherapp.view.forecastDetails.fragment.presenter.IForecastDetailsPresenter;
import com.example.weatherapp.view.forecastDetails.fragment.presenter.SafeForecastDetailsPresenter;
import com.example.weatherapp.view.forecastDetails.fragment.view.ForecastDetailsView;
import com.example.weatherapp.view.forecastDetails.fragment.view.InMainThreadForecastDetailsView;

public class ForecastDetailsFragment extends Fragment {
    private IForecastDetailsPresenter presenter;

    private View view;

    private ForecastDetailsToolbarButton btnToday;
    private ForecastDetailsToolbarButton btnTomorrow;
    private ForecastDetailsToolbarButton btnSeveralDays;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forecast_details, container, false);

        setupView();

        presenter =
                new AsyncForecastDetailsPresenter(
                        new SafeForecastDetailsPresenter(
                                new ForecastDetailsPresenter(
                                        new InMainThreadForecastDetailsView(
                                                new ForecastDetailsView(view)
                                        ),
                                        new GetSeveralDaysForecastUseCase(OpenWeatherApiProvider.get(view.getContext().getApplicationContext())),
                                        new TodayForecastMapper(getResources()),
                                        new TomorrowForecastMapper(getResources()),
                                        new SeveralDaysForecastMapper(getResources()),
                                        (SerializableCityLocation) getArguments().getSerializable(ForecastDetailsConst.CITY_LOCATION_KEY),
                                        getArguments().getInt(ForecastDetailsConst.UNIT_TYPE_KEY, ForecastUnitsType.CELSIUS.getValue()),
                                        FavoriteLocationRepositoryProvider.get(getContext().getApplicationContext()))));

        presenter.onCreate();

        return view;
    }

    private void setupView() {
        btnToday = view.findViewById(R.id.btn_forecast_details_toolbar_today);
        btnTomorrow = view.findViewById(R.id.btn_forecast_details_toolbar_tomorrow);
        btnSeveralDays = view.findViewById(R.id.btn_forecast_details_toolbar_several_days);

        ForecastDetailsToolbarViewGroup forecastDetailsToolbarViewGroup =
                view.findViewById(R.id.cl_forecast_details_toolbar);

        forecastDetailsToolbarViewGroup.addView(btnToday);
        forecastDetailsToolbarViewGroup.addView(btnTomorrow);
        forecastDetailsToolbarViewGroup.addView(btnSeveralDays);
        forecastDetailsToolbarViewGroup.addOnForecastToolbarButtonClickListener(
                view -> {
                    int viewId = view.getId();

                    if (viewId == btnToday.getId()) {
                        presenter.onTodayPressed();

                    } else if (viewId == btnTomorrow.getId()) {
                        presenter.onTomorrowPressed();

                    } else if (viewId == btnSeveralDays.getId()) {
                        presenter.onFiveDaysPressed();
                    }
                });
    }
}
