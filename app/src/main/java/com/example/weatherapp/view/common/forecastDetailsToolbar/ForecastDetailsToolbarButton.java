package com.example.weatherapp.view.common.forecastDetailsToolbar;

import android.content.Context;
import android.util.AttributeSet;

import com.example.weatherapp.R;

final public class ForecastDetailsToolbarButton extends androidx.appcompat.widget.AppCompatButton {

    public ForecastDetailsToolbarButton(Context context) {
        super(context);
    }

    public ForecastDetailsToolbarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setIsSelectedBackground(boolean selected) {
        if (selected) {
            float viewWithFilter = 1f;

            setAlpha(viewWithFilter);
            setTextColor(getResources().getColor(R.color.colorFocusedSimpleText));
            setBackground(getResources().getDrawable(R.drawable.forecast_details_toolbar_btn_selected));
        } else {
            float viewWithFilter = 0.8f;

            setAlpha(viewWithFilter);
            setTextColor(getResources().getColor(R.color.colorSimpleTextLight));
            setBackground(getResources().getDrawable(R.drawable.forecast_details_toolbar_btn_unselected));
        }
    }
}
