package com.example.weatherapp.locationList.view;

import android.os.Handler;
import android.os.Looper;

import com.example.weatherapp.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

public class InMainThreadFavoriteLocationListView implements IFavoriteLocationListView {
    private final IFavoriteLocationListView origin;
    private final Handler handler;

    public InMainThreadFavoriteLocationListView(IFavoriteLocationListView origin) {
        this.origin = origin;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void showSearchField() {
        handler.post(origin::showSearchField);
    }

    @Override
    public void updateFavoriteLocationsList(List<IFavoriteLocationItemDisplayModel> models) {
        handler.post(() -> origin.updateFavoriteLocationsList(models));
    }

    @Override
    public void setEmptyListMessageVisibility(boolean isVisible) {
        handler.post(() -> origin.setEmptyListMessageVisibility(isVisible));
    }
}
