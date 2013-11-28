package com.techery.spares.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


import com.techery.spares.annotations.Layout;
import com.techery.spares.annotations.MenuResource;
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
        if (objectGraph == null) {
            setupObjectGraph();
        }
        
        return objectGraph;
    }

    @Override
    public void inject(Object target) {
        getObjectGraph().inject(target);
    }

    private void setupObjectGraph() {
        assert(getApplication() instanceof Injector);
        objectGraph = ((Injector)getApplication()).getObjectGraph().plus(getModules().toArray());
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

        setupObjectGraph();        
        setupLayout();

        inject(this);

        Views.inject(this);

        afterCreateView(savedInstanceState);
    }

    public void setupLayout() {
        Layout layout = this.getClass().getAnnotation(Layout.class);
        if (layout != null) {
            setContentView(layout.value());
        }
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

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuResource menuResource = this.getClass().getAnnotation(MenuResource.class);
        if (menuResource != null) {
            getMenuInflater().inflate(menuResource.value(), menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }
    }

    protected List<Object> getModules() {
        List<Object> result = new ArrayList<Object>();
        result.add(new InjectingActivityModule(this, this));
        return result;
    }

    public void onEvent(Events.ReloadEvent reloadEvent) {

    }

    protected void afterCreateView(Bundle savedInstanceState) {

    }
}
