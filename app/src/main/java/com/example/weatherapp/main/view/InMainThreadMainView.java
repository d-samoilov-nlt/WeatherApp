package com.example.weatherapp.main.view;

import android.os.Handler;
import android.os.Looper;

public class InMainThreadMainView implements IMainView {
    private final IMainView origin;
    private final Handler handler;

    public InMainThreadMainView(IMainView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showTabs() {
        handler.post(origin::showTabs);
    }
}
