package com.zen.droidparts.module.analitics;

import com.zen.droidparts.application.AppInitializer;
import com.zen.droidparts.initializer.TestFlightInitializer;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class TestFlightModule {

    @Provides(type = Provides.Type.SET)
    AppInitializer provideTestFlight(TestFlightInitializer testFlightInitializer) {
        return testFlightInitializer;
    }
}
