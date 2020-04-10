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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.weatherapp.R;
import com.example.weatherapp.provider.FavoriteLocationRepositoryProvider;
import com.example.weatherapp.view.common.DelayedAfterTextChangedWatcher;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecastDetailsActivity;
import com.example.weatherapp.view.favoriteLocationForecastDetails.FavoriteLocationForecastDetailsConst;
import com.example.weatherapp.view.locationList.model.mapper.FavoriteLocationItemMapper;
import com.example.weatherapp.view.locationList.model.useCase.FilterFavoriteLocationListByCityNameUseCase;
import com.example.weatherapp.view.locationList.presenter.AsyncFavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.presenter.FavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.presenter.IFavoriteLocationListPresenter;
import com.example.weatherapp.view.locationList.view.FavoriteLocationListView;
import com.example.weatherapp.view.locationList.view.InMainThreadFavoriteLocationListView;

public class FavoriteLocationListFragment extends Fragment {
    private IFavoriteLocationListPresenter presenter;

    private View view;
    private ImageView ivSearchIcon;
    private EditText etSearchLocation;

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
                                                dm -> presenter.onLocationItemPressed(dm),
                                                view)
                                ),
                                FavoriteLocationRepositoryProvider.get(view.getContext().getApplicationContext()),
                                new FilterFavoriteLocationListByCityNameUseCase(),
                                new FavoriteLocationItemMapper(),
                                cityName -> {
                                    Intent intent =
                                            new Intent(getContext(), FavoriteLocationForecastDetailsActivity.class);
                                    intent.putExtra(FavoriteLocationForecastDetailsConst.CITY_NAME_KEY, cityName);
                                    getActivity().startActivity(intent);
                                }
                        ));


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
