package com.example.weatherapp.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.R;
import com.example.weatherapp.view.common.WeatherAppActivity;
import com.example.weatherapp.view.currenctLocation.CurrentLocationFragment;
import com.example.weatherapp.view.locationList.FavoriteLocationListFragment;
import com.example.weatherapp.view.main.presenter.AsyncMainPresenter;
import com.example.weatherapp.view.main.presenter.IMainPresenter;
import com.example.weatherapp.view.main.presenter.MainPresenter;
import com.example.weatherapp.view.main.presenter.SafeMainPresenter;
import com.example.weatherapp.view.main.view.IMainView;
import com.example.weatherapp.view.main.view.InMainThreadMainView;
import com.example.weatherapp.view.main.view.LocationTabFragmentAdapter;
import com.example.weatherapp.view.searchCity.SearchCityActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends WeatherAppActivity implements IMainView {
    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActions();

        presenter =
                new AsyncMainPresenter(
                        new SafeMainPresenter(
                                new MainPresenter(
                                        () -> startActivity(new Intent(MainActivity.this, SearchCityActivity.class)),
                                        new InMainThreadMainView(this))));

        presenter.onCreate();

    }

    private void setupActions() {
        findViewById(R.id.fab_main_check_weather_for_city).setOnClickListener(v -> presenter.onSearchByCityPressed());
    }

    @Override
    protected ViewGroup getCoordinatorContainerView() {
        return findViewById(R.id.cl_main_container);
    }

    @Override
    public void showTabs() {
        TabLayout tabLayout = findViewById(R.id.tl_main_location_tabs);
        ViewPager viewPager = findViewById(R.id.vp_main_location_view_pager);
        viewPager.setOffscreenPageLimit(2);

        LocationTabFragmentAdapter tabFragmentAdapter =
                new LocationTabFragmentAdapter(
                        getSupportFragmentManager());

        tabFragmentAdapter.addFragment(new FavoriteLocationListFragment(), getString(R.string.main_screen_tab_all_locations));
        tabFragmentAdapter.addFragment(new CurrentLocationFragment(), getString(R.string.main_screen_tab_current_location));

        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
