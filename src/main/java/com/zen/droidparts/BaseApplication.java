package com.zen.droidparts;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by zen on 11/15/13.
 */
public abstract class BaseApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        setObjectGraph(ObjectGraph.create(getModules()));
    }

    protected void setObjectGraph(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    protected abstract Object[] getModules();
}
