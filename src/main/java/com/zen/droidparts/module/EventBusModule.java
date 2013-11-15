package com.zen.droidparts.module;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;

@Module(library = true)
public class EventBusModule {
    @Provides
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }
}
