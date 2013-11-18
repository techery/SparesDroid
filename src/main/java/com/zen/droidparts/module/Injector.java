package com.zen.droidparts.module;

import dagger.ObjectGraph;

public interface Injector {
    public ObjectGraph getObjectGraph();
    public void inject(Object target);
}
