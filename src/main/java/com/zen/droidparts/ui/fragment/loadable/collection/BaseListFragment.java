package com.zen.droidparts.ui.fragment.loadable.collection;

import android.view.View;
import android.widget.AdapterView;

import com.zen.droidparts.adapter.DataListAdapter;

public abstract class BaseListFragment<T, ET, LV> extends CollectionFragment<T> implements AdapterView.OnItemClickListener {
    protected LV listView;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);

        setListView(findListView(rootView));
        setupOnItemClickListener(getListView());
    }

    public LV getListView() {
        return listView;
    }

    public void setListView(LV listView) {
        this.listView = listView;
        linkAdapter(this.listView);
    }

    @Override
    public void setDataAdapter(DataListAdapter<T> dataAdapter) {
        super.setDataAdapter(dataAdapter);
        getDataAdapter().setDataController(getDataController());
        if (getListView() != null) {
            linkAdapter(getListView());
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ET item = (ET) getDataAdapter().getItem(position);

        getEventBus().post(new DataListAdapter.Events.ItemSelectionEvent<ET>(item));
    }

    protected abstract void setupOnItemClickListener(LV listView);
    protected abstract void linkAdapter(LV listView);
    protected abstract LV findListView(View rootView);

}
