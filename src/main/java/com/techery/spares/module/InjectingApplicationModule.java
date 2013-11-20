package com.techery.spares.module;

import android.content.Context;

import com.techery.spares.application.BaseApplicationWithInjector;
import com.techery.spares.module.Annotations.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;

@Module(
        includes = {
                AndroidServicesModule.class,
                EventBusModule.class
        },
        library = true,
        complete = false)
public class InjectingApplicationModule {
    @Provides
    @Singleton
    @Application
    Context provideContext(BaseApplicationWithInjector baseApplicationWithInjector) {
        return baseApplicationWithInjector.getApplicationContext();
    }

    @Provides
    @Singleton
    @Application
    ObjectGraph provideObjectGraph(BaseApplicationWithInjector baseApplicationWithInjector) {
        return baseApplicationWithInjector.getObjectGraph();
    }

    @Provides
    @Singleton
    @Application
    Injector provideInjector(BaseApplicationWithInjector baseApplicationWithInjector) {
        return baseApplicationWithInjector;
    }
}
