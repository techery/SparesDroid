package com.zen.droidparts.utils;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by zen on 11/12/13.
 */
public class ComplexPreferenceStorage<T> extends PreferenceStorage {
    private final Class<T> typeClass;

    public ComplexPreferenceStorage(Context ctx, Class<T> typeClass) {
        super(ctx);
        this.typeClass = typeClass;
    }

    public T getObject(String key) {
        String json = getString(key);

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, typeClass);
        }

        return null;
    }

    public void putObject(String key, T obj) {
        Gson gson = new Gson();
        if (obj != null) {
            putString(key, gson.toJson(obj));
        } else {
            putString(key, null);
        }
    }
}
