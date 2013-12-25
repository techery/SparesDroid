package com.techery.spares.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;
import java.util.Locale;

public class FragmentTabsPagerAdapter extends FragmentPagerAdapter {

    private final List<TabItem> tabs;

    public static class TabItem {
        private final String title;
        private final Class fragmentClass;

        public TabItem(String title, Class fragmentClass) {
            this.title = title;
            this.fragmentClass = fragmentClass;
        }

        public String getTitle() {
            return title;
        }

        public Class getFragmentClass() {
            return fragmentClass;
        }
    }

    private Context context;

    public FragmentTabsPagerAdapter(Context context, FragmentManager fm, List<TabItem> items) {
        super(fm);
        this.tabs = items;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment) Fragment.instantiate(this.context, this.tabs.get(position).getFragmentClass().getName());
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