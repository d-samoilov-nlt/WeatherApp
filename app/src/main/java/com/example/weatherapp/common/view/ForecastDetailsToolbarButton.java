package com.example.weatherapp.common.view;

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
            setTextColor(getResources().getColor(R.color.colorFocusedSimpleText));
            setBackground(getResources().getDrawable(R.drawable.forecast_details_toolbar_btn_selected));
        } else {
            setTextColor(getResources().getColor(R.color.colorSimpleTextLight));
            setBackground(getResources().getDrawable(R.drawable.forecast_details_toolbar_btn_unselected));
        }
    }
}
