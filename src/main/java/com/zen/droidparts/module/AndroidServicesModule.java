package com.zen.droidparts.module;

import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;

import com.zen.droidparts.module.Specifiers.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(library = true, complete = false)
public class AndroidServicesModule {

    @Provides
    @Singleton
    LocationManager provideLocationManager(@Application Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(@Application Context context) {
        return (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    Resources provideResources(@Application Context context) {
        return context.getResources();
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(@Application Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
