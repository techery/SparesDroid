package com.techery.spares.module;

import android.content.Context;

import com.techery.spares.module.Specifiers.Activity;
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
    @Activity
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    @Singleton
    @Activity
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @Singleton
    @Activity
    public Injector provideActivityInjector() {
        return injector;
    }
}
