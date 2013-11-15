package com.zen.droidparts.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zen.droidparts.ui.activity.BaseActivity;

import butterknife.Views;
import de.greenrobot.event.EventBus;

public abstract class BaseFragment extends Fragment {
    private EventBus eventBus;

    public interface Events {
        class ReloadEvent {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentLayoutResource(), container, false);

        Views.inject(this, rootView);

        extractParams();

        afterCreateView(rootView);

        return rootView;
    }

    protected void extractParams() {

    }

    protected void afterCreateView(View rootView) {

    }

    protected abstract int getFragmentLayoutResource();

    @Override
    public void onResume() {
        super.onResume();
        getEventBus().registerSticky(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getEventBus().unregister(this);
    }

    public void onEvent(Events.ReloadEvent reloadEvent) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof BaseActivity)) {
            throw new IllegalArgumentException("BaseFragment have to be attached to instance of BaseActivity");
        }

        BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.getBaseApplication().getObjectGraph().inject(this);

        if (!shouldInheritEventBus()) {
            if (getEventBus() == null) {
                setEventBus(new EventBus());
            }
        }
    }

    protected boolean shouldInheritEventBus() {
        return false;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

}
