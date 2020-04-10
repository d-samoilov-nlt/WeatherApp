package com.example.weatherapp.view.favoriteLocationForecastDetails.view;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapp.R;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class FavoriteLocationForecastDetailsView implements IFavoriteLocationForecastDetailsView {
    private final View rootView;

    private TextView tvForecastShortDetails;
    private FrameLayout flForecastDetailsContainer;
    private ConstraintLayout clForecastLoadingProgressContainer;
    private ImageView ivFavoriteLocation;

    public FavoriteLocationForecastDetailsView(View rootView) {
        this.rootView = rootView;

        tvForecastShortDetails = rootView.findViewById(R.id.tv_favorite_location_forecast_toolbar_short_details);
        ivFavoriteLocation = rootView.findViewById(R.id.iv_favorite_location_forecast_toolbar_favorite);
        flForecastDetailsContainer = rootView.findViewById(R.id.fl_favorite_location_forecast_details);
        clForecastLoadingProgressContainer = rootView.findViewById(R.id.cl_favorite_location_forecast_details_loading_progress);
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

        tvForecastShortDetails.setText(
                String.format(
                        rootView.getResources().getString(stringResId),
                        dm.getCityName(),
                        dm.getTemp(),
                        dm.getForecast()));
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
    public void setLoadingProcess(boolean isLoading) {
        if (isLoading) {
            clForecastLoadingProgressContainer.setVisibility(View.VISIBLE);
            flForecastDetailsContainer.setVisibility(View.GONE);
        } else {
            clForecastLoadingProgressContainer.setVisibility(View.GONE);
            flForecastDetailsContainer.setVisibility(View.VISIBLE);
        }
    }
}
