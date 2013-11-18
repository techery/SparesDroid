package com.zen.droidparts;

import android.app.Application;

import com.zen.droidparts.module.Injector;

import java.util.List;

import dagger.ObjectGraph;

public abstract class BaseApplicationWithInjector extends Application implements Injector {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        this.objectGraph = ObjectGraph.create(getModules().toArray());

        inject(this);
    }

    protected abstract List<Object> getModules();

    @Override
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    @Override
    public void inject(Object target) {
        getObjectGraph().inject(target);
    }
}
