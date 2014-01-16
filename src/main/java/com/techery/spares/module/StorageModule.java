package com.techery.spares.module;

import android.content.SharedPreferences;

import com.techery.spares.storage.ObjectStorage;
import com.techery.spares.storage.complex_objects.ComplexStorageBuilder;
import com.techery.spares.storage.preferences.ObjectPreferenceStorage;
import com.techery.spares.storage.preferences.SimpleKeyValueStorage;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class StorageModule {
    @Provides
    SimpleKeyValueStorage provideSimpleKeyValueStorage(SharedPreferences preferences) {
        return new SimpleKeyValueStorage(preferences);
    }

    @Provides
    ComplexStorageBuilder provideComplexStorageBuilder(SimpleKeyValueStorage simpleKeyValueStorage) {
        return new ComplexStorageBuilder(simpleKeyValueStorage);
    }
}
