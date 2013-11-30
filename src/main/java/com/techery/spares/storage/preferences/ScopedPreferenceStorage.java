package com.techery.spares.storage.preferences;

import com.techery.spares.storage.ScopedStringStorage;

public class ScopedPreferenceStorage implements ScopedStringStorage {
    private final PreferenceStorage preferenceStorage;
    private final String key;

    public ScopedPreferenceStorage(PreferenceStorage preferenceStorage, String key) {
        this.preferenceStorage = preferenceStorage;
        this.key = key;
    }

    @Override
    public String get() {
        return this.preferenceStorage.get(key);
    }

    @Override
    public void put(final String value) {
        this.preferenceStorage.put(key, value);
    }
}
