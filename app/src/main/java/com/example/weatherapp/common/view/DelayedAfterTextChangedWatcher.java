package com.example.weatherapp.common.view;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;


public class DelayedAfterTextChangedWatcher implements TextWatcher {
    private final TextWatcher origin;
    private final Long delay;

    private Handler handler;
    private Runnable runnable;

    public DelayedAfterTextChangedWatcher(TextWatcher origin, Long delay) {
        this.origin = origin;
        this.delay = delay;

        handler = new Handler();
        runnable = () -> {
        };
    }

    public DelayedAfterTextChangedWatcher(TextWatcher origin) {
        this.origin = origin;
        this.delay = 1000L;

        handler = new Handler();
        runnable = () -> {
        };
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        origin.beforeTextChanged(s, start, count, after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        origin.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        handler.removeCallbacks(runnable);
        runnable = () -> {
            origin.afterTextChanged(s);
        };
        handler.postDelayed(runnable, delay);
    }
}
