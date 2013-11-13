package com.zen.droidparts.ui.fragment.loadable.collection;

import android.content.Context;
import android.view.View;

import com.zen.droidparts.adapter.BaseListAdapter;
import com.zen.droidparts.loader.BaseSimpleTaskLoader;

import java.util.List;

import de.greenrobot.event.EventBus;

public abstract class ArrayAdapterFragment<T> extends CollectionFragment<List<T>> {
    private BaseListAdapter<T> arrayAdapter;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);

        arrayAdapter = new BaseListAdapter<T>(getActivity(), getCellBuilder(), getEventBus());
    }

    protected BaseListAdapter<T> getAdapter() {
        return arrayAdapter;
    }

    @Override
    public void processResult(List<T> result) {
        for (T res : result) {
            arrayAdapter.add(res);
        }

        arrayAdapter.notifyDataSetChanged();
    }

    protected T getItemAtPosition(int position) {
        return getAdapter().getItem(position);
    }

    @Override
    public void setEventBus(EventBus eventBus) {
        super.setEventBus(eventBus);
        if (getAdapter() != null) {
            getAdapter().setEventBus(getEventBus());
        }
    }

    protected abstract BaseCell.CellBuilder getCellBuilder();

    protected static abstract class BaseListLoader<T> extends BaseSimpleTaskLoader<List<T>> {
        public BaseListLoader(Context context, LoadingTask<List<T>> loadingTask) {
            super(context, loadingTask);
        }
    }
}
