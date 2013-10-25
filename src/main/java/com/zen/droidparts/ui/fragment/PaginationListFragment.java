package com.zen.droidparts.ui.fragment;

import android.content.Context;

import java.util.List;

public abstract class PaginationListFragment<T, LV> extends BaseListFragment<T, LV> {
    protected static class PaginationListLoader<T> extends BaseAbstractLoader<List<T>> {
        private int currentPage;
        private final LoadingTask<T> loadingTask;

        public PaginationListLoader(Context context, LoadingTask<T> loadingTask) {
            super(context);
            this.loadingTask = loadingTask;
        }

        public interface LoadingTask<T> {
            List<T> call(int page);
        }

        @Override
        protected List<T> perform() {
            return this.loadingTask.call(getCurrentPage());
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
    }
}
