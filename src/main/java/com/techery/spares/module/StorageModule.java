package com.techery.spares.module;

import android.content.SharedPreferences;

import com.techery.spares.storage.complex_objects.ComplexStorageBuilder;
import com.techery.spares.storage.preferences.PreferenceStorage;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class StorageModule {
    @Provides
    PreferenceStorage providePreferenceStorage(SharedPreferences preferences) {
        return new PreferenceStorage(preferences);
    }

    @Provides
    ComplexStorageBuilder provideComplexStorageBuilder(PreferenceStorage preferenceStorage) {
        return new ComplexStorageBuilder(preferenceStorage);
    }
}
