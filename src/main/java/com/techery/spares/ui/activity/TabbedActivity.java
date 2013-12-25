package com.techery.spares.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.techery.spares.adapter.FragmentTabsPagerAdapter;

import java.util.List;

public abstract class TabbedActivity extends BaseActivity implements ActionBar.TabListener {
    ViewPager viewPager;

    @Override
    protected void afterCreateView(Bundle savedInstanceState) {
        viewPager = findViewPager();
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        setupTabs();
    }

    protected void setupTabs() {
        final ActionBar actionBar = getSupportActionBar();

        FragmentTabsPagerAdapter sectionsPagerAdapter = new FragmentTabsPagerAdapter(this, getSupportFragmentManager(), buildTabs());

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        actionBar.removeAllTabs();
        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(sectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    protected abstract List<FragmentTabsPagerAdapter.TabItem> buildTabs();

    protected abstract ViewPager findViewPager();

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
}
