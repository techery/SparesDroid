package com.zen.droidparts.utils;

/**
 * Created by zen on 11/15/13.
 */
public interface KeyValueStorage {
    public <T> T getObject(String key, Class<T> typeClass);
    public <T> void putObject(String key, T obj);
}
