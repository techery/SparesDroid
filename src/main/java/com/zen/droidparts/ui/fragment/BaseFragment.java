package com.zen.droidparts.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Views;
import de.greenrobot.event.EventBus;

/**
 *
 * Created by zen on 10/21/13.
 */
public abstract class BaseFragment extends Fragment {
    private EventBus eventBus;

    public BaseFragment() {
        eventBus = new EventBus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(getFragmentLayoutResourse(), container, false);

        Views.inject(this, rootView);

        afterCreateView(rootView);

        return rootView;
    }

    protected void afterCreateView(View rootView) {

    }

    protected abstract int getFragmentLayoutResourse();

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    protected EventBus getEventBus() {
        return eventBus;
    }
}
