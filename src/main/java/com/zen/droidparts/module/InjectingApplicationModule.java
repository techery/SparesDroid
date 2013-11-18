package com.zen.droidparts.module;

import android.content.Context;

import com.zen.droidparts.BaseApplicationWithInjector;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Module(library = true)
public class InjectingApplicationModule {
    private final BaseApplicationWithInjector baseApplicationWithInjector;
    private final Injector injector;

    public InjectingApplicationModule(BaseApplicationWithInjector baseApplicationWithInjector, Injector injector) {
        this.baseApplicationWithInjector = baseApplicationWithInjector;
        this.injector = injector;
    }

    @Provides
    @Singleton
    @Application
    Context provideContext() {
        return this.baseApplicationWithInjector.getApplicationContext();
    }

    @Provides
    @Singleton
    @Application
    ObjectGraph provideObjectGraph() {
        return baseApplicationWithInjector.getObjectGraph();
    }

    @Provides
    @Singleton
    @Application
    Injector provideInjector() {
        return injector;
    }

    @Qualifier
    @Target({FIELD, PARAMETER, METHOD})
    @Documented
    @Retention(RUNTIME)
    public @interface Application {

    }
}
