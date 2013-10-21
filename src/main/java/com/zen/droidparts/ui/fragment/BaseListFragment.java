package com.zen.droidparts.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

public abstract class BaseListFragment<T, E> extends ListFragment {
    private static final int MAIN_LOADER = 1;

    private LoaderManager.LoaderCallbacks<T> loaderCallbacks = new LoaderManager.LoaderCallbacks<T>() {
        @Override
        public Loader<T> onCreateLoader(int i, Bundle bundle) {
            return getLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<T> tLoader, T result) {
            processResult(result);
        }

        @Override
        public void onLoaderReset(Loader<T> tLoader) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        Loader loader = getLoaderManager().initLoader(MAIN_LOADER, null, loaderCallbacks);
        loader.forceLoad();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        E item = getItemAtPosition(position);

        onItemClick(item);
    }

    protected void reload() {
        Loader loader = getLoaderManager().restartLoader(MAIN_LOADER, null, loaderCallbacks);
        loader.forceLoad();
    }

    protected void onItemClick(E item) {

    }

    protected abstract Loader<T> getLoader(FragmentActivity activity);
    protected abstract E getItemAtPosition(int position);
    protected abstract void processResult(T result);
}
