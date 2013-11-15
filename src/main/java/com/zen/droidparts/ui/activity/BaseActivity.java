package com.zen.droidparts.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.zen.droidparts.BaseApplication;

import javax.inject.Inject;

import butterknife.Views;
import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends ActionBarActivity {
    public interface Events {
        class ReloadEvent {

        }
    }

    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getApplication() instanceof BaseApplication) {
            getBaseApplication().getObjectGraph().inject(this);
        }

        int contentResourсe = getContentViewResource();
        if (contentResourсe > 0) {
            setContentView(contentResourсe);
        }

        Views.inject(this);

        afterCreateView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEventBus().registerSticky(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getEventBus().unregister(this);
    }

    public BaseApplication getBaseApplication() {
        return (BaseApplication)getApplication();
    }

    public void onEvent(Events.ReloadEvent reloadEvent) {

    }

    protected abstract int getContentViewResource();
    protected abstract void afterCreateView(Bundle savedInstanceState);

    public synchronized EventBus getEventBus() {
        return this.eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
