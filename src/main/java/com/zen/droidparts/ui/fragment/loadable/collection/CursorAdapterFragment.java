package com.zen.droidparts.ui.fragment.loadable.collection;

import android.database.Cursor;

import com.zen.droidparts.adapter.BaseCursorListAdapter;

public abstract class CursorAdapterFragment<T, LV> extends BaseListFragment<Cursor, T, LV> {
    @Override
    protected void afterCreateView(android.view.View rootView) {
        super.afterCreateView(rootView);
        setDataAdapter(new BaseCursorListAdapter(getActivity()));
    }
}
