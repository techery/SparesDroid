package com.zen.droidparts.ui.fragment;

import android.content.Context;

import java.util.List;

public abstract class SimpleListFragment<T, LV> extends BaseListFragment<T, LV> {
    public static class SimpleListLoader<T> extends BaseListLoader<T> {
        public SimpleListLoader(Context context, LoadingTask<List<T>> loadingTask) {
            super(context, loadingTask);
        }
    }

    @Override
    protected void preProcessResult(List<T> result) {
        getAdapter().clear();
    }
}
