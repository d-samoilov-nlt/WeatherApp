package com.example.weatherapp.view.forecastDetails.fragment.view;

import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

import java.util.ArrayList;

public class ForecastDetailsView implements IForecastDetailsView {
    private final ForecastDetailsRVAdapter todayForecastDetailsRVAdapter;
    private final ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter;
    private final ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter;
    private final RecyclerView rvForecastDetails;
    private final ConstraintLayout clForecastContent;
    private final ConstraintLayout clLoadingProgress;
    private final LinearLayout llLoadingErrorView;

    public ForecastDetailsView(View rootView) {
        todayForecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());
        tomorrowForecastDetailsRVAdapter = new ForecastDetailsRVAdapter(new ArrayList<>());
        forecastSeveralDaysDetailsRvAdapter = new ForecastSeveralDaysDetailsRvAdapter(new ArrayList<>());

        clForecastContent = rootView.findViewById(R.id.cl_forecast_details_content);
        clLoadingProgress = rootView.findViewById(R.id.cl_forecast_loading_progress);
        llLoadingErrorView = rootView.findViewById(R.id.cl_forecast_error_message);
        rvForecastDetails = rootView.findViewById(R.id.rv_forecast_details_forecast);


        setupRv(rootView);
    }

    private void setupRv(View rootView) {
        rvForecastDetails.setLayoutManager(
                new LinearLayoutManager(
                        rootView.getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        rvForecastDetails.setAdapter(todayForecastDetailsRVAdapter);
    }

    @Override
    public void showForecastToday(IDayForecastDisplayModel dm) {
        rvForecastDetails.setAdapter(todayForecastDetailsRVAdapter);

        todayForecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        todayForecastDetailsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showForecastTomorrow(IDayForecastDisplayModel dm) {
        rvForecastDetails.setAdapter(tomorrowForecastDetailsRVAdapter);

        tomorrowForecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        tomorrowForecastDetailsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showForecastForSeveralDays(ISeveralDaysForecastDisplayModel dm) {
        rvForecastDetails.setAdapter(forecastSeveralDaysDetailsRvAdapter);

        forecastSeveralDaysDetailsRvAdapter.updateForecastList(dm.getDaysForecastList());
        forecastSeveralDaysDetailsRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetailsLoadingProgress(boolean isLoading) {
        if (isLoading) {
            clForecastContent.setVisibility(View.GONE);
            clLoadingProgress.setVisibility(View.VISIBLE);
        } else {
            clForecastContent.setVisibility(View.VISIBLE);
            clLoadingProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoadingError(boolean isError) {
        if (isError) {
            llLoadingErrorView.setVisibility(View.VISIBLE);
            clForecastContent.setVisibility(View.GONE);
            clLoadingProgress.setVisibility(View.GONE);
        } else {
            llLoadingErrorView.setVisibility(View.GONE);
            clForecastContent.setVisibility(View.VISIBLE);
            clLoadingProgress.setVisibility(View.VISIBLE);
        }
    }
}
