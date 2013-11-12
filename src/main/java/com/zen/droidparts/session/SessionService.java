package com.zen.droidparts.session;

import android.content.Context;

import com.zen.droidparts.utils.ComplexPreferenceStorage;

public class SessionService<SESSION_CLASS extends SessionService.Session> extends ComplexPreferenceStorage<SESSION_CLASS> {
    private static String SESSION_KEY = "SESSION_KEY";

    public SessionService(Context ctx, Class<SESSION_CLASS> cls) {
        super(ctx, cls);
    }

    public static class Session {

    }

    public void saveSession(SESSION_CLASS session) {
        putObject(SESSION_KEY, session);
    }

    public void destroySession() {
        putObject(SESSION_KEY, null);
    }

    public SESSION_CLASS getActiveSession() {
        return getObject(SESSION_KEY);
    }

    public boolean hasActiveSession() {
        return getActiveSession() != null;
    }
}
