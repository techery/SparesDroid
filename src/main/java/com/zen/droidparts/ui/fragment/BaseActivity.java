package com.zen.droidparts.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.Views;
import de.greenrobot.event.EventBus;

/**
 * Created by zen on 10/27/13.
 */
public abstract class BaseActivity extends FragmentActivity {

    private EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setEventBus(EventBus.getDefault());

        int contentResourse = getContentViewResource();
        if (contentResourse > 0) {
            setContentView(contentResourse);
        }

        Views.inject(this);

        afterCreateView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEventBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getEventBus().unregister(this);
    }

    protected abstract int getContentViewResource();

    protected abstract void afterCreateView(Bundle savedInstanceState);

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
