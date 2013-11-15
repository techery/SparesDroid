package com.zen.droidparts.module;

import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by zen on 11/15/13.
 */
public class RetrofitModule {
    private final String serverAddress;

    public RetrofitModule(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Provides
    String provideServerAddress() {
        return this.serverAddress;
    }

    @Provides
    RestAdapter provideRestAdapter(String serverAddress) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setServer(serverAddress)
                .build();
        return adapter;
    }
}
