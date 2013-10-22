package com.zen.droidparts.ui.fragment;

import android.content.Context;

import java.util.List;

/**
 * Created by zen on 10/22/13.
 */
public abstract class PaginationListFragment<T> extends BaseListFragment<T> {
    protected static class PaginationListLoader<T> extends BaseListLoader<T> {
        private int currentPage;
        private final LoadingTask<T> loadingTask;
        public interface LoadingTask<T> {
            List<T> call(int page);
        }

        public PaginationListLoader(Context context, LoadingTask<T> loadingTask) {
            super(context);
            this.loadingTask = loadingTask;
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
