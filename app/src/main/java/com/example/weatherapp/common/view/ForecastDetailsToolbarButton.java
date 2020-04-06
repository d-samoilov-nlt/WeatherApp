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

    @Override
    public void setSelected(boolean selected) {
        if (selected) {
            setBackgroundResource(R.drawable.forecast_details_toolbar_btn_selected);
        } else {
            setBackgroundResource(R.drawable.forecast_details_toolbar_btn_unselected);
        }
    }
}
