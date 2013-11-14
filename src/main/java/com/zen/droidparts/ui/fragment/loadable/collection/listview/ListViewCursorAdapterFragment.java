package com.zen.droidparts.ui.fragment.loadable.collection.listview;

import android.widget.ListView;

import com.zen.droidparts.ui.fragment.loadable.collection.CursorAdapterFragment;

/**
 * Created by zen on 11/14/13.
 */
public abstract class ListViewCursorAdapterFragment<T> extends CursorAdapterFragment<T, ListView> {
    @Override
    protected void setupOnItemClickListener(ListView listView) {
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void linkAdapter(ListView listView) {
        listView.setAdapter(getDataAdapter());
    }
}
