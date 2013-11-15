package com.zen.droidparts.session;

import com.zen.droidparts.utils.KeyValueStorage;

public class SessionService<SESSION_CLASS> {
    private static String SESSION_KEY = "SESSION_KEY";

    private final Class<SESSION_CLASS> typeClass;
    private final KeyValueStorage storage;

    public SessionService(KeyValueStorage storage, Class<SESSION_CLASS> cls) {
        this.typeClass = cls;
        this.storage = storage;
    }

    public void saveSession(SESSION_CLASS session) {
        this.storage.putObject(SESSION_KEY, session);
    }

    public void destroySession() {
        this.storage.putObject(SESSION_KEY, null);
    }

    public SESSION_CLASS getActiveSession() {
        return this.storage.getObject(SESSION_KEY, this.typeClass);
    }

    public boolean hasActiveSession() {
        return getActiveSession() != null;
    }
}
