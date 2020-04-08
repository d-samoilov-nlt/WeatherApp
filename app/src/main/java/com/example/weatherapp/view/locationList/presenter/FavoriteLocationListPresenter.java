package com.example.weatherapp.view.locationList.presenter;

import com.example.weatherapp.data.model.favoriteLocation.IFavoriteLocationCacheData;
import com.example.weatherapp.data.repository.IFavoriteLocationRepository;
import com.example.weatherapp.domain.exception.NotFoundException;
import com.example.weatherapp.view.locationList.model.IFavoriteLocationItemDisplayModel;
import com.example.weatherapp.view.locationList.model.mapper.IFavoriteLocationItemMapper;
import com.example.weatherapp.view.locationList.model.useCase.IFilterFavoriteLocationListByCityNameUseCase;
import com.example.weatherapp.view.locationList.view.IFavoriteLocationListView;
import com.example.weatherapp.view.searchCity.model.ILaunchForecastDetailsScreenUseCase;

import java.util.ArrayList;
import java.util.List;

public class FavoriteLocationListPresenter implements IFavoriteLocationListPresenter {
    private final IFavoriteLocationListView view;
    private final IFavoriteLocationRepository favoriteLocationRepository;
    private final IFilterFavoriteLocationListByCityNameUseCase filterFavoriteLocationListByCityNameUseCase;
    private final IFavoriteLocationItemMapper favoriteLocationItemMapper;
    private final ILaunchForecastDetailsScreenUseCase launchForecastDetailsScreenUseCase;

    private boolean isSearchFieldAlreadyVisible;
    private List<IFavoriteLocationCacheData> favoriteLocationCacheDataList;
    private List<IFavoriteLocationItemDisplayModel> favoriteLocationItemDisplayModels;

    public FavoriteLocationListPresenter(
            IFavoriteLocationListView view,
            IFavoriteLocationRepository favoriteLocationRepository,
            IFilterFavoriteLocationListByCityNameUseCase filterFavoriteLocationListByCityNameUseCase,
            IFavoriteLocationItemMapper favoriteLocationItemMapper,
            ILaunchForecastDetailsScreenUseCase launchForecastDetailsScreenUseCase) {

        this.view = view;
        this.favoriteLocationRepository = favoriteLocationRepository;
        this.filterFavoriteLocationListByCityNameUseCase = filterFavoriteLocationListByCityNameUseCase;
        this.favoriteLocationItemMapper = favoriteLocationItemMapper;
        this.launchForecastDetailsScreenUseCase = launchForecastDetailsScreenUseCase;

        this.isSearchFieldAlreadyVisible = false;
        this.favoriteLocationCacheDataList = new ArrayList<>();
        this.favoriteLocationItemDisplayModels = new ArrayList<>();
    }

    @Override
    public void onStart() {

        try {
            favoriteLocationCacheDataList = favoriteLocationRepository.loadAll();
        } catch (NotFoundException ignore) {
            // nop
        }

        if (favoriteLocationCacheDataList.isEmpty()) {
            view.setEmptyListMessageVisibility(true);
        } else {
            view.setEmptyListMessageVisibility(false);

            favoriteLocationItemDisplayModels = favoriteLocationItemMapper.map(favoriteLocationCacheDataList);
            view.updateFavoriteLocationsList(favoriteLocationItemDisplayModels);
        }
    }

    @Override
    public void onSearchIconPressed() {
        if (!isSearchFieldAlreadyVisible) {
            isSearchFieldAlreadyVisible = true;
            view.showSearchField();
        }
    }

    @Override
    public void onSearchDataEntered(String data) {
        List<IFavoriteLocationItemDisplayModel> filteredLocationItemModels =
                filterFavoriteLocationListByCityNameUseCase
                        .filter(data, favoriteLocationItemDisplayModels);

        view.updateFavoriteLocationsList(
                filterFavoriteLocationListByCityNameUseCase.filter(
                        data,
                        favoriteLocationItemDisplayModels));

        view.setEmptyListMessageVisibility(filteredLocationItemModels.isEmpty());
    }

    @Override
    public void onLocationItemPressed(IFavoriteLocationItemDisplayModel dm) {
        launchForecastDetailsScreenUseCase.launch(dm.getShortDetailsDisplayModel().getCityName());
    }
}
