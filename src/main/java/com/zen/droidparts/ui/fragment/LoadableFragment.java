package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.noveogroup.android.log.Logger;


public abstract class LoadableFragment<T> extends BaseFragment {
    private static final int MAIN_LOADER = 1;

    public interface Events {
        public class ReloadEvent {}
    }

    @Override
    public void onResume() {
        super.onResume();

        getLoaderManager().initLoader(MAIN_LOADER, null, loaderCallbacks);
    }

    protected void reload() {
        getLoaderManager().restartLoader(MAIN_LOADER, null, loaderCallbacks);
    }


    protected void onEvent(Events.ReloadEvent reloadEvent) {
        reload();
    }

    protected abstract Loader<T> getLoader(FragmentActivity activity);
    protected abstract void processResult(T result);
    protected abstract void processError(Loader<T> tLoader);
    protected abstract void showLoadingIndication();
    protected abstract void hideLoadingIndication();

    private LoaderManager.LoaderCallbacks<T> loaderCallbacks = new LoaderManager.LoaderCallbacks<T>() {
        @Override
        public Loader<T> onCreateLoader(int i, Bundle bundle) {
            showLoadingIndication();
            return getLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<T> tLoader, T result) {
            if (result != null) {
                processResult(result);
            } else {
                processError(tLoader);
            }

            hideLoadingIndication();
        }

        @Override
        public void onLoaderReset(Loader<T> tLoader) {
            BaseAbstractLoader<T> loader = (BaseAbstractLoader<T>) tLoader;
            loader.getLastError();
        }
    };

    protected static abstract class BaseAbstractLoader<T> extends AsyncTaskLoader<T> {
        private Throwable lastError;
        private T cachedResult;

        public BaseAbstractLoader(Context context) {
            super(context);
            onContentChanged();
        }

        @Override
        protected void onReset() {
            super.onReset();

            this.cachedResult = null;
        }

        @Override
        protected void onStartLoading() {
            if(this.cachedResult != null) {
                deliverResult(this.cachedResult);
            } else {
                forceLoad();
            }
        }

        @Override
        public T loadInBackground() {
            try {
                this.cachedResult = perform();
                return this.cachedResult;
            } catch (Exception e) {
                lastError = e;
                logException(e);
                return null;
            }
        }

        private void logException(Exception e) {
            if (getLogger() != null) {
                getLogger().e(e);
            }
        }

        protected abstract T perform();

        public Throwable getLastError() {
            return lastError;
        }

        public Logger getLogger() {
            return null;
        }

        public void setLogger(Logger logger) {

        }
    }

    protected static abstract class BaseLoader<T> extends BaseAbstractLoader<T> {

        private final LoadingTask<T> loadingTask;

        public interface LoadingTask<T> {
            T call();
        }

        public BaseLoader(Context context, LoadingTask<T> loadingTask) {
            super(context);
            this.loadingTask = loadingTask;
        }

        protected T perform() {
            return this.loadingTask.call();
        }

    }
}
