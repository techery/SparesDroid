package com.techery.spares.storage.complex_objects;

import com.google.gson.Gson;
import com.techery.spares.storage.ObjectStorage;
import com.techery.spares.storage.ScopedStringStorage;

public class ComplexPreferenceStorage<T> implements ObjectStorage<T> {

    private final ScopedStringStorage storage;
    private final Class<T> typeClass;

    public ComplexPreferenceStorage(ScopedStringStorage storage, Class<T> objectClass) {
        this.storage = storage;
        this.typeClass = objectClass;
    }

    public T get() {
        String json = this.storage.get();

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, typeClass);
        }

        return null;
    }

    public void put(T obj) {
        Gson gson = new Gson();
        if (obj != null) {
            this.storage.put(gson.toJson(obj));
        } else {
            this.storage.put(null);
        }
    }
}
