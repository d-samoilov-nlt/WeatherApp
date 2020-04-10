package com.example.weatherapp.view.forecastDetails.fragment.view;

import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public class ForecastDetailsView implements IForecastDetailsView {
    private final ForecastDetailsRVAdapter todayForecastDetailsRVAdapter;
    private final ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter;
    private final ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter;
    private final RecyclerView rvForecastDetails;
    private final ConstraintLayout clForecastContent;
    private final ConstraintLayout clLoadingProgress;
    private final LinearLayout llLoadingErrorView;

    public ForecastDetailsView(
            ForecastDetailsRVAdapter todayForecastDetailsRVAdapter,
            ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter,
            ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter,
            RecyclerView rvForecastDetails,
            ConstraintLayout clForecastContent,
            ConstraintLayout clLoadingProgress,
            LinearLayout llLoadingErrorView) {

        this.todayForecastDetailsRVAdapter = todayForecastDetailsRVAdapter;
        this.tomorrowForecastDetailsRVAdapter = tomorrowForecastDetailsRVAdapter;
        this.forecastSeveralDaysDetailsRvAdapter = forecastSeveralDaysDetailsRvAdapter;
        this.rvForecastDetails = rvForecastDetails;
        this.clForecastContent = clForecastContent;
        this.clLoadingProgress = clLoadingProgress;
        this.llLoadingErrorView = llLoadingErrorView;
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
