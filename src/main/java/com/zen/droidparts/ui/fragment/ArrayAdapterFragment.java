package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class ArrayAdapterFragment<T> extends CollectionFragment<List<T>> {
    private BaseListAdapter<T> arrayAdapter;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);

        arrayAdapter = new BaseListAdapter<T>(getActivity(), getCellBuilder());
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

    protected abstract void showErrorState();
    protected abstract void preProcessResult(List<T> result);
    protected abstract BaseCell.CellBuilder getCellBuilder();

    protected static abstract class BaseListLoader<T> extends BaseLoader<List<T>> {
        public BaseListLoader(Context context, LoadingTask<List<T>> loadingTask) {
            super(context, loadingTask);
        }
    }

    protected class BaseListAdapter<T> extends ArrayAdapter<T> {
        private final BaseCell.CellBuilder cellBuilder;

        public BaseListAdapter(Context context, BaseCell.CellBuilder cellBuilder) {
            super(context, -1);
            this.cellBuilder = cellBuilder;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BaseCell cell = (BaseCell) convertView;
            T item = getItem(position);

            if (cell == null) {
                cell = cellBuilder.build(getContext(), item);
                cell.setEventBus(getEventBus());
            }

            cell.prepareForReuse();
            cell.fillWithItem(item);


            return (View) cell;
        }
    }
}
