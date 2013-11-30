package com.techery.spares.storage.preferences;

import android.content.SharedPreferences;

public class PreferenceStorage{
    private final SharedPreferences appSharedPrefs;

    public PreferenceStorage(final SharedPreferences preferences) {
        this.appSharedPrefs = preferences;
    }

    public String get(final String key) {
        return this.appSharedPrefs.getString(key, null);
    }

    public void put(final String key, String value) {
        SharedPreferences.Editor prefsEditor = this.appSharedPrefs.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }
}

