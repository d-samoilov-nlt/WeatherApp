package com.example.weatherapp.view.currenctLocation.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.weatherapi.data.ForecastUnitsType;
import com.example.weatherapp.R;
import com.example.weatherapp.view.forecastDetails.fragment.model.forecast.shortDetails.IForecastShortDetailsDisplayModel;

public class CurrentLocationView implements ICurrentLocationView {
    private final View rootView;

    private TextView tvSearchingProgress;
    private TextView tvSearchingError;
    private TextView tvToolbarShortDetails;

    private ImageView ivFavoriteLocation;

    private Button btnTryAgain;
    private ProgressBar pbSearchingProgress;

    public CurrentLocationView(View rootView) {
        this.rootView = rootView;

        btnTryAgain = rootView.findViewById(R.id.btn_fragment_current_location_try_again);
        ivFavoriteLocation = rootView.findViewById(R.id.iv_current_location_toolbar_favorite);
        tvSearchingProgress = rootView.findViewById(R.id.tv_current_location_searching_progress);
        pbSearchingProgress = rootView.findViewById(R.id.pb_current_location_searching_progress);
        tvSearchingError = rootView.findViewById(R.id.tv_current_location_error_msg);
        tvToolbarShortDetails = rootView.findViewById(R.id.tv_current_location_toolbar_short_details);
    }

    @Override
    public void setIsSearchingLocationProcess(boolean isProcess) {
        int visibility = isProcess ? View.VISIBLE : View.GONE;

        pbSearchingProgress.setVisibility(visibility);
        tvSearchingProgress.setVisibility(visibility);
    }

    @Override
    public void setIsPermissionRequiredError(boolean isError) {
        int visibility = isError ? View.VISIBLE : View.GONE;

        tvSearchingError.setVisibility(visibility);
        btnTryAgain.setVisibility(visibility);
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

        tvToolbarShortDetails.setText(
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

}
