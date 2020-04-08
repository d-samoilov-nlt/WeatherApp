package com.example.weatherapp.view.locationList.view;

import android.widget.EditText;
import android.widget.TextView;

import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FavoriteLocationListView implements IFavoriteLocationListView {
    private final EditText etSearchField;
    private final FavoriteLocationListRVAdapter locationListRVAdapter;
    private final TextView tvNoItemsMessage;

    public FavoriteLocationListView(
            EditText etSearchField,
            FavoriteLocationListRVAdapter locationListRVAdapter,
            TextView tvNoItemsMessage) {
        this.etSearchField = etSearchField;
        this.locationListRVAdapter = locationListRVAdapter;
        this.tvNoItemsMessage = tvNoItemsMessage;
    }

    @Override
    public void showSearchField() {
        etSearchField.setVisibility(VISIBLE);
    }

    @Override
    public void updateFavoriteLocationsList(List<IFavoriteLocationItemDisplayModel> models) {
        locationListRVAdapter.updateForecastList(models);
        locationListRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void setEmptyListMessageVisibility(boolean isVisible) {
        int visibility = isVisible ? VISIBLE : GONE;
        tvNoItemsMessage.setVisibility(visibility);
    }
}
