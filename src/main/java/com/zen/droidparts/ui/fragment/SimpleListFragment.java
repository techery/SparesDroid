package com.zen.droidparts.ui.fragment;

import android.content.Context;

import java.util.List;

/**
 * Created by zen on 10/22/13.
 */
public abstract class SimpleListFragment<T, LV> extends BaseListFragment<T, LV> {
    public static class SimpleListLoader<T> extends BaseListLoader<T> {
        private final LoadingTask<T> loadingTask;
        public interface LoadingTask<T> {
            List<T> call();
        }

        public SimpleListLoader(Context context, LoadingTask<T> loadingTask) {
            super(context);
            this.loadingTask = loadingTask;
        }

        @Override
        protected List<T> perform() {
            return this.loadingTask.call();
        }
    }

    @Override
    protected void preProcessResult(List<T> result) {
        getAdapter().clear();
    }
}
