package com.zen.droidparts.module;

import android.content.Context;

import com.zen.droidparts.BaseApplication;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

@Module(library = true, complete = false)
public class AppContextModule {
    @Provides
    Context provideContext(BaseApplication baseApplication) {
        return baseApplication.getApplicationContext();
    }

    @Provides
    ObjectGraph provideRootObjectGraph(BaseApplication baseApplication) {
        return baseApplication.getObjectGraph();
    }
}
