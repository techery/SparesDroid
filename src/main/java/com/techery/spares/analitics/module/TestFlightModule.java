package com.techery.spares.analitics.module;

import com.techery.spares.application.AppInitializer;
import com.techery.spares.application.BaseApplicationWithInjector;
import com.techery.spares.analitics.initializers.TestFlightInitializer;

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
