package com.zen.droidparts.analitics.module;

import com.zen.droidparts.application.AppInitializer;
import com.zen.droidparts.application.BaseApplicationWithInjector;
import com.zen.droidparts.analitics.initializers.TestFlightInitializer;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class TestFlightModule {

    @Provides(type = Provides.Type.SET)
    AppInitializer provideTestFlight(TestFlightInitializer.TestFlightConfig testFlightConfig, Lazy<BaseApplicationWithInjector> applicationWithInjector) {
        return new TestFlightInitializer(testFlightConfig, applicationWithInjector);
    }
}
