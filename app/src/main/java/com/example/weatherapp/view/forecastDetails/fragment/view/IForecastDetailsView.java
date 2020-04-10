package com.example.weatherapp.view.forecastDetails.fragment.view;

import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public interface IForecastDetailsView {
    void showForecastToday(IDayForecastDisplayModel dm);

    void showForecastTomorrow(IDayForecastDisplayModel dm);

    void showForecastForSeveralDays(ISeveralDaysForecastDisplayModel dm);

    void showDetailsLoadingProgress(boolean isLoading);

    void showLoadingError(boolean isError);
}
