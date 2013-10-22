package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
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

    protected BaseListAdapter<T> getArrayAdapter() {
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

    protected T getItemAtPosition(int position) {
        return getArrayAdapter().getItem(position);
    }

    protected abstract void preProcessResult(List<T> result);
    protected abstract BaseCell.CellBuilder getCellBuilder();

    protected static abstract class BaseListLoader<T> extends AsyncTaskLoader<List<T>> {
        private Throwable lastError;

        public BaseListLoader(Context context) {
            super(context);
        }

        @Override
        public List<T> loadInBackground() {
            try {
                return perform();
            } catch (Exception e) {
                lastError = e;
                return null;
            }
        }

        protected abstract List<T> perform();

        public Throwable getLastError() {
            return lastError;
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
