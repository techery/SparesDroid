package com.techery.spares.storage.preferences;

import com.google.common.base.Optional;
import com.techery.spares.storage.ObjectStorage;

public class ObjectPreferenceStorage implements ObjectStorage<String> {
    private final SimpleKeyValueStorage simpleKeyValueStorage;
    private final String key;

    public ObjectPreferenceStorage(SimpleKeyValueStorage simpleKeyValueStorage, String key) {
        this.simpleKeyValueStorage = simpleKeyValueStorage;
        this.key = key;
    }

    @Override
    public Optional<String> get() {
        return Optional.fromNullable(this.simpleKeyValueStorage.get(key));
    }

    @Override
    public void put(String obj) {
        this.simpleKeyValueStorage.put(key, obj);
    }

    @Override
    public void destroy() {
        this.simpleKeyValueStorage.remove(key);
    }
}
