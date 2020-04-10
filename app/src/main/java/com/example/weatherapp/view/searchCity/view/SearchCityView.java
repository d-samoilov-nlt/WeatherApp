package com.example.weatherapp.view.searchCity.view;

import android.view.View;
import android.widget.Button;

import com.example.weatherapp.R;
import com.example.weatherapp.view.common.LocationEditText;

public class SearchCityView implements ISearchCityView {
    private final LocationEditText etEnterLocationData;
    private final Button btnViewWeather;
    private final View rootView;

    public SearchCityView(View rootView) {
        this.rootView = rootView;

        etEnterLocationData = rootView.findViewById(R.id.et_search_city_location);
        btnViewWeather = rootView.findViewById(R.id.btn_search_city_view_weather);
    }

    @Override
    public void setViewWeatherBtnEnabled(boolean isEnabled) {
        btnViewWeather.setEnabled(isEnabled);
    }

    @Override
    public void setDeviceLocationInfo(String locationInfo) {
        etEnterLocationData.setText(locationInfo);
    }

    @Override
    public void showCityNotFoundError(boolean isError) {
        if (isError) {
            etEnterLocationData.setError(rootView.getResources().getString(R.string.error_city_not_found));
        } else {
            etEnterLocationData.setError(null);
        }
    }

    @Override
    public void setLocationIconEnabled(boolean isEnabled) {
        etEnterLocationData.setLocationIconEnabled(isEnabled);
    }
}
