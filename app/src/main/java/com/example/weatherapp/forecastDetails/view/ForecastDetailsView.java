package com.example.weatherapp.forecastDetails.view;

import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.weatherapp.data.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.data.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public class ForecastDetailsView implements IForecastDetailsView {
    private final ForecastDetailsRVAdapter todayForecastDetailsRVAdapter;
    private final ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter;
    private final ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter;
    private final ConstraintLayout clForecastContent;
    private final ConstraintLayout clLoadingProgress;
    private final LinearLayout llLoadingErrorView;

    public ForecastDetailsView(
            ForecastDetailsRVAdapter todayForecastDetailsRVAdapter,
            ForecastDetailsRVAdapter tomorrowForecastDetailsRVAdapter,
            ForecastSeveralDaysDetailsRvAdapter forecastSeveralDaysDetailsRvAdapter,
            ConstraintLayout clForecastContent,
            ConstraintLayout clLoadingProgress,
            LinearLayout llLoadingErrorView) {

        this.todayForecastDetailsRVAdapter = todayForecastDetailsRVAdapter;
        this.tomorrowForecastDetailsRVAdapter = tomorrowForecastDetailsRVAdapter;
        this.forecastSeveralDaysDetailsRvAdapter = forecastSeveralDaysDetailsRvAdapter;
        this.clForecastContent = clForecastContent;
        this.clLoadingProgress = clLoadingProgress;
        this.llLoadingErrorView = llLoadingErrorView;
    }

    @Override
    public void showForecastToday(IDayForecastDisplayModel dm) {
        todayForecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        todayForecastDetailsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showForecastTomorrow(IDayForecastDisplayModel dm) {
        tomorrowForecastDetailsRVAdapter.updateForecastList(dm.getForecastList());
        tomorrowForecastDetailsRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void showForecastForSeveralDays(ISeveralDaysForecastDisplayModel dm) {
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
