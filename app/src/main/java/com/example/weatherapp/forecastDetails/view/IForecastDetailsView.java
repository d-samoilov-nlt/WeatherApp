package com.example.weatherapp.forecastDetails.view;

import com.example.weatherapp.data.forecast.fullForecast.IDayForecastDisplayModel;
import com.example.weatherapp.data.forecast.fullForecast.ISeveralDaysForecastDisplayModel;

public interface IForecastDetailsView {
    void showForecastToday(IDayForecastDisplayModel dm);

    void showForecastTomorrow(IDayForecastDisplayModel dm);

    void showForecastForSeveralDays(ISeveralDaysForecastDisplayModel dm);

    void showDetailsLoadingProgress(boolean isLoading);

    void showLoadingError(boolean isError);
}
