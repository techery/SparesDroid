package com.techery.spares.session;

import com.techery.spares.storage.ObjectStorage;
import com.techery.spares.storage.complex_objects.ComplexStorageBuilder;

import de.greenrobot.event.EventBus;

public class SessionHolder<SESSION_CLASS> {
    private static String SESSION_KEY = "SESSION_KEY";

    public interface Events {
        public class SessionChanged {

        }

        public class SessionCreated<SESSION_CLASS> extends SessionChanged {
            private final SESSION_CLASS session;

            public SessionCreated(SESSION_CLASS session) {
                this.session = session;
            }

            public SESSION_CLASS getSession() {
                return session;
            }
        }

        public class SessionDestroyed  extends SessionChanged {

        }
    }

    private final ObjectStorage<SESSION_CLASS> objectStorage;
    private final EventBus eventBus;

    public SessionHolder(ComplexStorageBuilder builder, Class<SESSION_CLASS> cls, EventBus bus) {
        this.objectStorage = builder.build(SESSION_KEY, cls);
        this.eventBus = bus;
    }

    public void saveSession(SESSION_CLASS session) {
        this.objectStorage.put(session);
        this.eventBus.post(new Events.SessionCreated<SESSION_CLASS>(session));
    }

    public void destroySession() {
        this.objectStorage.put(null);
        this.eventBus.post(new Events.SessionDestroyed());
    }

    public SESSION_CLASS getActiveSession() {
        return this.objectStorage.get();
    }

    public boolean hasActiveSession() {
        return getActiveSession() != null;
    }
}
