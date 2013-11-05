package com.zen.droidparts.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.zen.droidparts.adapter.FragmentTabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zen on 11/5/13.
 */
public abstract class TabbedActivity extends BaseActivity implements ActionBar.TabListener {
    ViewPager viewPager;
    
    @Override
    protected void afterCreateView(Bundle savedInstanceState) {
        setupActionBar();
    }

    private void setupActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        List<FragmentTabsPagerAdapter.TabItem> sections = new ArrayList<FragmentTabsPagerAdapter.TabItem>();

        FragmentTabsPagerAdapter sectionsPagerAdapter = new FragmentTabsPagerAdapter(getSupportFragmentManager(), buildTabs());
        viewPager = findViewPager();
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

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
