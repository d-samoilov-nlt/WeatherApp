package com.example.weatherapp.view.locationList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.view.common.DelayedAfterTextChangedWatcher;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecasDetailsConst;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecastDetailsActivity;
import com.example.weatherapp.view.locationList.model.mapper.FavoriteLocationItemMapper;
import com.example.weatherapp.view.locationList.model.useCase.FilterFavoriteLocationListByCityNameUseCase;
import com.example.weatherapp.view.locationList.presenter.AsyncFavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.presenter.FavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.presenter.IFavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.view.FavoriteLocationListRVAdapter;
import com.example.weatherapp.view.locationList.view.FavoriteLocationListView;
import com.example.weatherapp.view.locationList.view.InMainThreadFavoriteLocationListView;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class FavoriteLocationListFragment extends Fragment {
    private View view;

    private IFavoriteLocationListPresenter presenter;

    private ImageView ivSearchIcon;
    private EditText etSearchLocation;
    private TextView tvNoItemsMessage;
    private FavoriteLocationListRVAdapter locationListRVAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite_location_list, container, false);
        setupView();
        setupActions();

        presenter =
                new AsyncFavoriteLocationListPresenter(
                        new FavoriteLocationListPresenter(
                                new InMainThreadFavoriteLocationListView(
                                        new FavoriteLocationListView(
                                                etSearchLocation,
                                                locationListRVAdapter,
                                                tvNoItemsMessage)
                                ),
                                FavoriteLocationRepositoryProvider.get(getContext().getApplicationContext()),
                                new FilterFavoriteLocationListByCityNameUseCase(),
                                new FavoriteLocationItemMapper(),
                                cityName -> {
                                    Intent intent =
                                            new Intent(getContext(), FavoriteLocationForecastDetailsActivity.class);
                                    intent.putExtra(FavoriteLocationForecasDetailsConst.CITY_NAME_KEY, cityName);
                                    getActivity().startActivity(intent);
                                }
                        ),
                        Executors.newCachedThreadPool()
                );


        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        etSearchLocation.setText("");
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    private void setupView() {
        ivSearchIcon = view.findViewById(R.id.iv_location_list_toolbar_search);
        etSearchLocation = view.findViewById(R.id.et_location_list_toolbar_search);
        tvNoItemsMessage = view.findViewById(R.id.tv_location_list_no_items_message);

        RecyclerView rvLocationList = view.findViewById(R.id.rv_location_list);
        rvLocationList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        locationListRVAdapter = new FavoriteLocationListRVAdapter(
                dm -> presenter.onLocationItemPressed(dm),
                new ArrayList<>());
        rvLocationList.setAdapter(locationListRVAdapter);

    }

    private void setupActions() {
        ivSearchIcon.setOnClickListener(v -> presenter.onSearchIconPressed());
        etSearchLocation.addTextChangedListener(
                new DelayedAfterTextChangedWatcher(
                        new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                // nop
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                // nop
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                presenter.onSearchDataEntered(s.toString());
                            }
                        }
                )
        );
    }
}
