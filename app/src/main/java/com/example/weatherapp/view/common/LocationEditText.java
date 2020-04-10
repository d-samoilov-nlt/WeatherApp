package com.example.weatherapp.view.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.weatherapp.R;

public final class LocationEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnTouchListener {
    private OnLocationIconClickListener onLocationIconClickListener;
    private boolean isLocationIconEnabled;

    public LocationEditText(Context context) {
        super(context);
        setOnTouchListener(this);
        this.isLocationIconEnabled = false;
    }

    public LocationEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        this.isLocationIconEnabled = false;
    }

    public void setOnLocationIconClickListener(OnLocationIconClickListener onLocationIconClickListener) {
        this.onLocationIconClickListener = onLocationIconClickListener;
    }

    public void setLocationIconEnabled(boolean isLocationIconEnabled) {
        this.isLocationIconEnabled = isLocationIconEnabled;

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, getRightDrawableId(), 0);
        this.setHint(getHintTextResId());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (this.getRight() - this.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                changeLocationIconStateAndNotify();
                return true;
            }
            return false;
        }
        return false;
    }

    private void changeLocationIconStateAndNotify() {
        isLocationIconEnabled = !isLocationIconEnabled;
        onLocationIconClickListener.onClick(isLocationIconEnabled);

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, getRightDrawableId(), 0);
        this.setHint(getHintTextResId());
    }

    private int getRightDrawableId() {
        if (isLocationIconEnabled) {
            return R.drawable.ic_my_location_24dp_enabled;
        } else {
            return R.drawable.ic_my_location_24dp_disabled;
        }
    }

    private int getHintTextResId() {
        if (isLocationIconEnabled) {
            return R.string.search_city_screen_searching_location_hint;
        } else {
            return R.string.search_city_screen_location_hint;
        }
    }

    public interface OnLocationIconClickListener {
        void onClick(boolean isEnabled);
    }
}
