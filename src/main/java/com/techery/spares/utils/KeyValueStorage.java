package com.techery.spares.utils;

public interface KeyValueStorage {
    public <T> T getObject(String key, Class<T> typeClass);
    public <T> void putObject(String key, T obj);
}
