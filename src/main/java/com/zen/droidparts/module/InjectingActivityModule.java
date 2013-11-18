package com.zen.droidparts.module;

import android.content.Context;

import com.zen.droidparts.ui.activity.BaseActivity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

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

    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Documented
    @Retention(RUNTIME)
    public @interface Activity {
    }
}
