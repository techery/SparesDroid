package com.techery.spares.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.techery.spares.module.Annotations.Global;
import com.techery.spares.module.InjectingActivityModule;
import com.techery.spares.module.Injector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Views;
import dagger.ObjectGraph;
import de.greenrobot.event.EventBus;

public abstract class BaseActivity extends ActionBarActivity implements Injector {
    public static final String PARAMS = "BaseActivity#PARAMS";
    private ObjectGraph objectGraph;

    @Override
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    @Override
    public void inject(Object target) {
        getObjectGraph().inject(target);
    }

    public interface Events {
        class ReloadEvent {

        }
    }

    @Inject
    @Global
    protected EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert(getApplication() instanceof Injector);

        objectGraph = ((Injector)getApplication()).getObjectGraph().plus(getModules().toArray());

        inject(this);

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
        this.eventBus.registerSticky(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.eventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        this.objectGraph = null;
        super.onDestroy();
    }

    protected List<Object> getModules() {
        List<Object> result = new ArrayList<Object>();
        result.add(new InjectingActivityModule(this, this));
        return result;
    }

    public void onEvent(Events.ReloadEvent reloadEvent) {

    }

    protected abstract int getContentViewResource();

    protected void afterCreateView(Bundle savedInstanceState) {

    }
}
