package com.zen.droidparts.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

/**
 * Created by zen on 10/28/13.
 */
public class DataController<T> implements LoaderManager.LoaderCallbacks<T> {
    private static final int DEFAULT_LOADER = 1;

    public interface DataControllerCallBack<T> {
        public void processResult(T result);
        public void processError(Throwable throwable);
    }

    public interface LoaderFactory {
        public Loader createLoader(Context context, Bundle bundle);
    }

    public interface Events {
        public class ReloadEvent {}
    }

    private LoaderManager loaderManager;
    private final Context context;
    private final int loaderID;
    private final LoaderFactory loaderFactory;
    private DataControllerCallBack<T> dataControllerCallBack;

    public DataController(Context context, LoaderManager loaderManager, LoaderFactory factory) {
        this(context, loaderManager, DEFAULT_LOADER, factory);
    }

    public DataController(Context context, LoaderManager loaderManager, int loaderID, LoaderFactory factory) {
        this.loaderManager = loaderManager;
        this.context = context;
        this.loaderID = loaderID;
        this.loaderFactory = factory;
    }

    public LoaderManager getLoaderManager() {
        return loaderManager;
    }

    public Context getContext() {
        return context;
    }

    public int getLoaderID() {
        return loaderID;
    }

    public DataControllerCallBack<T> getDataControllerCallBack() {
        return dataControllerCallBack;
    }

    public void setDataControllerCallBack(DataControllerCallBack<T> dataControllerCallBack) {
        this.dataControllerCallBack = dataControllerCallBack;
    }

    public final void load() {
        getLoaderManager().initLoader(getLoaderID(), null, this);
    }

    public final void reload() {
        getLoaderManager().restartLoader(getLoaderID(), null, this);
    }

    @Override
    public final Loader<T> onCreateLoader(int i, Bundle bundle) {
        return this.loaderFactory.createLoader(getContext(), bundle);
    }

    @Override
    public final void onLoadFinished(Loader<T> objectLoader, T o) {
        if (o != null) {
            getDataControllerCallBack().processResult(o);
        } else {
            getDataControllerCallBack().processError(null);
        }
    }

    @Override
    public final void onLoaderReset(Loader<T> objectLoader) {
        objectLoader.reset();
    }
}
