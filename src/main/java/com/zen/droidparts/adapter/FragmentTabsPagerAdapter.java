package com.zen.droidparts.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import java.util.Locale;

public class FragmentTabsPagerAdapter extends FragmentPagerAdapter {

    private final List<TabItem> tabs;

    public interface TabItem {
        public String getTitle();
        public Fragment getFragment();
    }

    public FragmentTabsPagerAdapter(FragmentManager fm, List<TabItem> items) {
        super(fm);
        this.tabs = items;
    }

    @Override
    public Fragment getItem(int position) {
        return this.tabs.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return this.tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        return this.tabs.get(position).getTitle().toUpperCase(l);
    }
}