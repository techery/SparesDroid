package com.techery.spares.ui.fragment;

import android.view.View;

/**
 * Created by zen on 11/18/13.
 */
public interface ConfigurableFragment {
    public void afterCreateView(View rootView);
    public abstract int getFragmentLayoutResource();
}

