package com.techery.spares.storage.complex_objects;

import com.techery.spares.storage.ObjectStorage;
import com.techery.spares.storage.preferences.PreferenceStorage;
import com.techery.spares.storage.preferences.ScopedPreferenceStorage;

public class ComplexStorageBuilder {
    private final PreferenceStorage preferenceStorage;

    public ComplexStorageBuilder(PreferenceStorage preferenceStorage) {
        this.preferenceStorage = preferenceStorage;
    }

    public final <T> ObjectStorage<T> build(final String key, final Class<T> objectClass) {
        return new ComplexPreferenceStorage<T>(getStorage(key), objectClass);
    }

    private ScopedPreferenceStorage getStorage(final String key) {
        return new ScopedPreferenceStorage(preferenceStorage,  key);
    }
}
