package com.example.weatherapp.view.main.view;

import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.R;
import com.example.weatherapp.view.currenctLocation.CurrentLocationFragment;
import com.example.weatherapp.view.locationList.FavoriteLocationListFragment;
import com.google.android.material.tabs.TabLayout;

public class MainView implements IMainView {
    private final TabLayout tabLayout;
    private final ViewPager viewPager;
    private final FragmentManager fragmentManager;
    private final View rootView;

    public MainView(View rootView, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.rootView = rootView;

        tabLayout = rootView.findViewById(R.id.tl_main_location_tabs);
        viewPager = rootView.findViewById(R.id.vp_main_location_view_pager);
    }

    @Override
    public void showTabs() {
        viewPager.setOffscreenPageLimit(2);

        LocationTabFragmentAdapter tabFragmentAdapter =
                new LocationTabFragmentAdapter(fragmentManager);

        tabFragmentAdapter.addFragment(
                new FavoriteLocationListFragment(),
                rootView
                        .getResources()
                        .getString(R.string.main_screen_tab_all_locations));

        tabFragmentAdapter.addFragment(
                new CurrentLocationFragment(),
                rootView
                        .getResources()
                        .getString(R.string.main_screen_tab_current_location));

        viewPager.setAdapter(tabFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
