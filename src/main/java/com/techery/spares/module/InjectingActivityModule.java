package com.techery.spares.module;

import android.content.Context;

import com.techery.spares.module.Annotations.ForActivity;
import com.techery.spares.ui.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class InjectingActivityModule {
    private final BaseActivity activity;
    private final Injector injector;

    public InjectingActivityModule(BaseActivity activity, Injector injector) {
        this.activity = activity;
        this.injector = injector;
    }

    @Provides
    @Singleton
    @ForActivity
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    @Singleton
    @ForActivity
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @Singleton
    @ForActivity
    public Injector provideActivityInjector() {
        return injector;
    }
}
