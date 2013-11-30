package com.techery.spares.storage;

public interface ObjectStorage<T> {
    T get();
    void put(T obj);
}
