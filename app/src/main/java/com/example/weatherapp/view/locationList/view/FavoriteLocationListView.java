package com.example.weatherapp.view.locationList.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.weatherapp.util.FocusUtils.requestFocusWithSoftKeyboard;

public class FavoriteLocationListView implements IFavoriteLocationListView {
    private final OnLocationItemClickListener onLocationItemClickListener;

    private EditText etSearchField;
    private FavoriteLocationListRVAdapter locationListRVAdapter;
    private TextView tvNoItemsMessage;

    public FavoriteLocationListView(
            OnLocationItemClickListener onLocationItemClickListener,
            View rootView) {

        this.onLocationItemClickListener = onLocationItemClickListener;

        etSearchField = rootView.findViewById(R.id.et_location_list_toolbar_search);
        tvNoItemsMessage = rootView.findViewById(R.id.tv_location_list_no_items_message);

        setupRv(rootView);
    }

    private void setupRv(View rootView) {
        RecyclerView rvLocationList = rootView.findViewById(R.id.rv_location_list);

        rvLocationList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        locationListRVAdapter =
                new FavoriteLocationListRVAdapter(
                        onLocationItemClickListener::onClick,
                        new ArrayList<>());

        rvLocationList.setAdapter(locationListRVAdapter);
    }

    @Override
    public void showSearchField() {
        etSearchField.setVisibility(VISIBLE);
        requestFocusWithSoftKeyboard(etSearchField);
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

    public interface OnLocationItemClickListener {
        void onClick(IFavoriteLocationItemDisplayModel dm);
    }
}
