package com.techery.spares.utils;

import android.content.Context;

import com.google.gson.Gson;

public class ComplexPreferenceStorage implements KeyValueStorage {

    private final PreferenceStorage preferenceStorage;

    public ComplexPreferenceStorage(Context context) {
        this(new PreferenceStorage(context));
    }

    public ComplexPreferenceStorage(PreferenceStorage preferenceStorage) {
        this.preferenceStorage = preferenceStorage;
    }

    public <T> T getObject(String key, Class<T> typeClass) {
        String json = this.preferenceStorage.getString(key);

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, typeClass);
        }

        return null;
    }

    public <T> void putObject(String key, T obj) {
        Gson gson = new Gson();
        if (obj != null) {
            this.preferenceStorage.putString(key, gson.toJson(obj));
        } else {
            this.preferenceStorage.putString(key, null);
        }
    }
}
