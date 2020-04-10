package com.example.weatherapp.view.forecastDetails.containerActivity.view;

import android.view.View;
import android.widget.TextView;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapp.R;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class ForecastDetailsContainerView implements IForecastDetailsContainerView {
    private final View rootView;

    private TextView tvShortForecastDetails;

    public ForecastDetailsContainerView(View rootView) {
        this.rootView = rootView;
        tvShortForecastDetails = rootView.findViewById(R.id.tv_forecast_details_container_toolbar_short_details);
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

        tvShortForecastDetails.setText(
                String.format(
                        rootView.getResources().getString(stringResId),
                        dm.getCityName(),
                        dm.getTemp(),
                        dm.getForecast()));
    }
}
