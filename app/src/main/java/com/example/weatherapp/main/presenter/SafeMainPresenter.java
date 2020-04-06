package com.example.weatherapp.main.presenter;

public class SafeMainPresenter implements IMainPresenter {
    private final IMainPresenter origin;

    public SafeMainPresenter(IMainPresenter origin) {
        this.origin = origin;
    }

    @Override
    public void onCreate() {
        try {
            origin.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchByCityPressed() {
        try {
            origin.onSearchByCityPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
