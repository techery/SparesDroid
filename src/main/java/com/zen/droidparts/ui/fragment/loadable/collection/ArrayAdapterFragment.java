package com.zen.droidparts.ui.fragment.loadable.collection;

import com.zen.droidparts.adapter.BaseArrayListAdapter;

import java.util.List;

public abstract class ArrayAdapterFragment<T, LV> extends InjectingListFragment<List<T>, T, LV> {
    @Override
    public void afterCreateView(android.view.View rootView) {
        super.afterCreateView(rootView);
        setDataAdapter(new BaseArrayListAdapter<T>(getActivity()));
    }


}
