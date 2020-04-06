package com.example.weatherapp.main;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.R;
import com.example.weatherapp.common.view.WeatherAppActivity;
import com.example.weatherapp.currenctLocation.CurrentLocationFragment;
import com.example.weatherapp.locationList.LocationListFragment;
import com.example.weatherapp.main.presenter.AsyncMainPresenter;
import com.example.weatherapp.main.presenter.IMainPresenter;
import com.example.weatherapp.main.presenter.MainPresenter;
import com.example.weatherapp.main.presenter.SafeMainPresenter;
import com.example.weatherapp.main.view.IMainView;
import com.example.weatherapp.main.view.InMainThreadMainView;
import com.example.weatherapp.main.view.LocationTabFragmentAdapter;
import com.example.weatherapp.service.ConnectivityReceiver;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.Executors;

public class MainActivity extends WeatherAppActivity implements IMainView {
    private IMainPresenter presenter;
    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActions();
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        presenter =
                new AsyncMainPresenter(
                        new SafeMainPresenter(
                                new MainPresenter(
                                        null, // TODO : add SearchCityActivity launching
                                        new InMainThreadMainView(this))),
                        Executors.newCachedThreadPool()
                );

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
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void showTabs() {
        TabLayout tabLayout = findViewById(R.id.tl_main_location_tabs);
        ViewPager viewPager = findViewById(R.id.vp_main_location_view_pager);
        viewPager.setOffscreenPageLimit(2);

        LocationTabFragmentAdapter tabFragmentAdapter =
                new LocationTabFragmentAdapter(
                        getSupportFragmentManager());

        tabFragmentAdapter.addFragment(new LocationListFragment(), getString(R.string.main_screen_tab_all_locations));
        tabFragmentAdapter.addFragment(new CurrentLocationFragment(), getString(R.string.main_screen_tab_current_location));

        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
