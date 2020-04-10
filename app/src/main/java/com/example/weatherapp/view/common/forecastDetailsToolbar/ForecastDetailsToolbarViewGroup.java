package com.example.weatherapp.view.common.forecastDetailsToolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public final class ForecastDetailsToolbarViewGroup extends ConstraintLayout implements View.OnClickListener {
    private final List<ForecastDetailsToolbarButton> forecastDetailsToolbarButtons;
    private OnForecastToolbarButtonClickListener onForecastToolbarButtonClickListener;

    public ForecastDetailsToolbarViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        forecastDetailsToolbarButtons = new ArrayList<>();
    }

    public ForecastDetailsToolbarViewGroup(Context context) {
        super(context);
        forecastDetailsToolbarButtons = new ArrayList<>();
    }

    @Override
    public void addView(View child) {
        if (!(child instanceof ForecastDetailsToolbarButton)) {
            throw new IllegalArgumentException("ForecastDetailsToolbarButton is required");
        }

        ForecastDetailsToolbarButton forecastDetailsToolbarButton = (ForecastDetailsToolbarButton) child;
        forecastDetailsToolbarButton.setOnClickListener(this);

        if (forecastDetailsToolbarButtons.isEmpty()) {
            forecastDetailsToolbarButton.setIsSelectedBackground(true);
        } else {
            forecastDetailsToolbarButton.setIsSelectedBackground(false);
        }

        forecastDetailsToolbarButtons.add(forecastDetailsToolbarButton);
    }

    public void addOnForecastToolbarButtonClickListener(OnForecastToolbarButtonClickListener listener) {
        this.onForecastToolbarButtonClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        for (ForecastDetailsToolbarButton button : forecastDetailsToolbarButtons) {
            if (button.getId() == v.getId()) {
                button.setIsSelectedBackground(true);

                if (onForecastToolbarButtonClickListener != null) {
                    onForecastToolbarButtonClickListener.onClicked(button);
                }
            } else {
                button.setIsSelectedBackground(false);
            }
        }
    }

    public interface OnForecastToolbarButtonClickListener {
        void onClicked(View view);
    }
}
