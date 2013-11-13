package com.zen.droidparts.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.zen.droidparts.loader.BaseAbstractLoader;


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

}
