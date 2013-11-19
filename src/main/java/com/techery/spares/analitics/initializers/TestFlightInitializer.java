package com.techery.spares.analitics.initializers;

import com.testflightapp.lib.TestFlight;
import com.techery.spares.application.AppInitializer;
import com.techery.spares.application.BaseApplicationWithInjector;

import dagger.Lazy;

public class TestFlightInitializer implements AppInitializer {
    public static class TestFlightConfig {
        private final String apiKey;

        public TestFlightConfig(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getApiKey() {
            return apiKey;
        }
    }

    final TestFlightConfig testFlightConfig;
    final Lazy<BaseApplicationWithInjector> app;

    public TestFlightInitializer(TestFlightConfig testFlightConfig, Lazy<BaseApplicationWithInjector> applicationWithInjector) {
        this.testFlightConfig = testFlightConfig;
        this.app = applicationWithInjector;
    }

    @Override
    public void initialize() {
        TestFlight.takeOff(app.get(), testFlightConfig.getApiKey());
    }
}
