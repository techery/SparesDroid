package com.zen.droidparts.ui.fragment.loadable.collection;

import android.content.Context;

import java.util.List;

public abstract class SimpleListFragment<T, LV> extends BaseListFragment<T, LV> {
    public static class SimpleListLoader<T> extends BaseListLoader<T> {
        public SimpleListLoader(Context context, LoadingTask<List<T>> loadingTask) {
            super(context, loadingTask);
        }
    }

    @Override
    public void processResult(List<T> result) {
        getAdapter().clear();
        super.processResult(result);
    }
}
