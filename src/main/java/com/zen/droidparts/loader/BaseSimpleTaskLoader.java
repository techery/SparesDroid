package com.zen.droidparts.loader;

import android.content.Context;

/**
 * Created by zen on 10/28/13.
 */
public class BaseSimpleTaskLoader<T> extends BaseAbstractLoader<T> {
    private final LoadingTask<T> loadingTask;

    public interface LoadingTask<T> {
        T call();
    }

    public BaseSimpleTaskLoader(Context context, LoadingTask<T> loadingTask) {
        super(context);
        this.loadingTask = loadingTask;
    }

    protected T perform() {
        return this.loadingTask.call();
    }
}