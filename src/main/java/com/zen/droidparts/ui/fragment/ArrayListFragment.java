package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class ArrayListFragment<T> extends BaseListFragment<List<T>, T> {
    private ArrayAdapter<T> arrayAdapter;
    private boolean isFreshLoad = true;
    private int currentPage = 1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        arrayAdapter = new BaseListAdapter<T>(getActivity(), getCellBuilder());
    }

    @Override
    protected T getItemAtPosition(int position) {
        return arrayAdapter.getItem(position);
    }

    @Override
    protected void processResult(List<T> result) {
        if (isFreshLoad) {
            setListAdapter(arrayAdapter);
            isFreshLoad = false;
        }

        for (T res : result) {
            arrayAdapter.add(res);
        }
        arrayAdapter.notifyDataSetChanged();
    }

    public void fullReload() {
        isFreshLoad = true;
        arrayAdapter.clear();
        setListAdapter(null);
        reload();
    }

    public void loadNextPage() {
        currentPage += 1;
        reload();
    }

    protected abstract BaseCell.CellBuilder getCellBuilder();

    protected static class BaseAsyncTaskLoader<T> extends AsyncTaskLoader<List<T>> {
        public interface LoadingTask<T> {
            List<T> call();
        }

        private final LoadingTask<T> loadingTask;

        private Throwable lastError;

        public BaseAsyncTaskLoader(Context context, LoadingTask<T> loadingTask) {
            super(context);
            this.loadingTask = loadingTask;
        }

        @Override
        public List<T> loadInBackground() {
            try {
                return this.loadingTask.call();
            } catch (Exception e) {
                lastError = e;
                return null;
            }
        }

        public Throwable getLastError() {
            return lastError;
        }
    }

    protected static interface BaseCell<T> {
        public void fillWithItem(T item);

        public interface CellBuilder {
            BaseCell build(Context c);
        }
    }

    protected static class BaseListAdapter<T> extends ArrayAdapter<T> {

        private final BaseCell.CellBuilder cellBuilder;

        public BaseListAdapter(Context context, BaseCell.CellBuilder cellBuilder) {
            super(context, -1);
            this.cellBuilder = cellBuilder;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            BaseCell cell = (BaseCell) convertView;

            if (cell == null) {
                cell = cellBuilder.build(getContext());
            }

            T item = getItem(position);

            cell.fillWithItem(item);

            return (View) cell;
        }
    }
}
