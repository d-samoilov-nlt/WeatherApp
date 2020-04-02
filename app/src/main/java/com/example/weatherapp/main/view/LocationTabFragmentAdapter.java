package com.example.weatherapp.main.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocationTabFragmentAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentTabList;
    private final List<String> fragmentTabNameList;

    public LocationTabFragmentAdapter(
            @NonNull FragmentManager fm) {

        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.fragmentTabList = new ArrayList<>();
        this.fragmentTabNameList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String tabName) {
        fragmentTabNameList.add(tabName);
        fragmentTabList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentTabList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTabNameList.get(position);
    }
}
