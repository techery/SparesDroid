package com.zen.droidparts.module;

import android.content.Context;

import com.zen.droidparts.application.BaseApplicationWithInjector;
import com.zen.droidparts.module.Specifiers.Application;

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
