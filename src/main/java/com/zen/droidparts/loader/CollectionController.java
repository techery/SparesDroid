package com.zen.droidparts.loader;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import java.util.List;

/**
 * Created by zen on 10/28/13.
 */
public class CollectionController<T> extends DataController<List<T>> {
    public CollectionController(Context context, LoaderManager loaderManager, LoaderFactory factory) {
        super(context, loaderManager, factory);
    }
}
