package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import de.greenrobot.event.EventBus;

public abstract class CollectionFragment<T> extends BaseFragment {
    private static final int MAIN_LOADER = 1;

    public interface Events {
        public class ReloadEvent {

        }

        public class ItemSelectionEvent<T> {
            private final T item;

            public ItemSelectionEvent(T item) {
                this.item = item;
            }

            public T getItem() {
                return item;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Loader loader = getLoaderManager().initLoader(MAIN_LOADER, null, loaderCallbacks);
        loader.forceLoad();

        showLoadingIndication();
    }

    protected void reload() {
        Loader loader = getLoaderManager().restartLoader(MAIN_LOADER, null, loaderCallbacks);
        loader.forceLoad();

        showLoadingIndication();
    }

    //Events
    protected void onEvent(Events.ReloadEvent reloadEvent) {
        reload();
    }

    protected abstract Loader<T> getLoader(FragmentActivity activity);
    protected abstract void processResult(T result);
    protected abstract void showLoadingIndication();
    protected abstract void hideLoadingIndication();

    private LoaderManager.LoaderCallbacks<T> loaderCallbacks = new LoaderManager.LoaderCallbacks<T>() {
        @Override
        public Loader<T> onCreateLoader(int i, Bundle bundle) {
            return getLoader(getActivity());
        }

        @Override
        public void onLoadFinished(Loader<T> tLoader, T result) {
            processResult(result);
            hideLoadingIndication();
        }

        @Override
        public void onLoaderReset(Loader<T> tLoader) {

        }
    };

    public interface BaseCell<T> {
        public void fillWithItem(T item);
        public void prepareForReuse();
        public void setEventBus(EventBus eventBus);

        public interface CellBuilder<T> {
            BaseCell build(Context c, T item);
        }
    }
}
