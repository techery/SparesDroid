package com.zen.droidparts.module;

import com.zen.droidparts.networking.NetworkingModuleConfig;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(library = true, complete = false)
public class RetrofitModule {
    
    @Provides
    RestAdapter provideRestAdapter(NetworkingModuleConfig config) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setServer(config.getServerAddress())
                .build();
        return adapter;
    }
}
