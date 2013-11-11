package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.support.v4.content.Loader;
import android.view.View;

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
    protected void processResult(List<T> result) {
        preProcessResult(result);
        
        for (T res : result) {
            arrayAdapter.add(res);
        }

        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void processError(Loader<List<T>> listLoader) {
        showErrorState();
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

    protected abstract void showErrorState();
    protected abstract void preProcessResult(List<T> result);
    protected abstract BaseCell.CellBuilder getCellBuilder();

    protected static abstract class BaseListLoader<T> extends BaseLoader<List<T>> {
        public BaseListLoader(Context context, LoadingTask<List<T>> loadingTask) {
            super(context, loadingTask);
        }
    }
}
