package com.twochooseone.android.hearthstonehub;

import android.app.Application;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import android.content.SharedPreferences;

@Singleton
public class Datastore {

    private static final String DEVICE_VERSION = "DeviceVersion";

    SharedPreferences sharedPreferences;

    public Datastore(Application app) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    private SharedPreferences getPrefs() {
        return sharedPreferences;
    }
    public int getVersion() {
        return getPrefs().getInt(DEVICE_VERSION, 0);
    }
    public void persistVersion(int version) {
        getEditor().putInt(DEVICE_VERSION, version).commit();
    }
}

