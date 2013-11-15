package com.zen.droidparts.utils;

import android.content.Context;

import com.google.gson.Gson;

public class ComplexPreferenceStorage extends PreferenceStorage implements KeyValueStorage {

    public ComplexPreferenceStorage(Context ctx) {
        super(ctx);
    }

    public <T> T getObject(String key, Class<T> typeClass) {
        String json = getString(key);

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, typeClass);
        }

        return null;
    }

    public <T> void putObject(String key, T obj) {
        Gson gson = new Gson();
        if (obj != null) {
            putString(key, gson.toJson(obj));
        } else {
            putString(key, null);
        }
    }
}
