package com.techery.spares.adapter;

import android.content.Context;

import java.util.List;

import dagger.ObjectGraph;

public class AdapterBuilder {
    private final ObjectGraph objectGraph;
    private final Context context;

    public AdapterBuilder(ObjectGraph objectGraph, Context context) {
        this.objectGraph = objectGraph;
        this.context = context;
    }

    public <T> BaseArrayAdapter build(List<T> objects, Class<? extends Cell<T>> cellClass) {
        return new BaseArrayAdapter<T>(this.context, objectGraph, objects, cellClass);
    }
}
