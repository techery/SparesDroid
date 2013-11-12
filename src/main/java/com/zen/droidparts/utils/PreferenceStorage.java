package com.zen.droidparts.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceStorage {
    private final Context context;

    public PreferenceStorage(Context ctx) {
        this.context = ctx;
    }

    public String getString(String key) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.context.getApplicationContext());
        return appSharedPrefs.getString(key, null);
    }

    public void putString(String key, String value) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }
}
