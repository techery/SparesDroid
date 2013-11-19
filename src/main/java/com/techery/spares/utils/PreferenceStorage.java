package com.techery.spares.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceStorage {

    private final SharedPreferences appSharedPrefs;

    public PreferenceStorage(Context context) {
        this.appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public String getString(String key) {
        return this.appSharedPrefs.getString(key, null);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor prefsEditor = this.appSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }
}

