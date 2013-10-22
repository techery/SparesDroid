package com.zen.droidparts.ui.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.zen.droidparts.R;

public abstract class BaseListFragment<T, LV> extends ArrayAdapterFragment<T> implements AdapterView.OnItemClickListener {
    protected LV listView;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);

        setListView(findListView(rootView));
        setupOnItemClickListener(getListView());
    }

    protected LV findListView(View rootView) {
        return (LV) rootView.findViewById(R.id.content_list);
    }

    public LV getListView() {
        return listView;
    }

    public void setListView(LV listView) {
        this.listView = listView;
        linkAdapter(this.listView);
    }

    protected abstract void setupOnItemClickListener(LV listView);
    protected abstract void linkAdapter(LV listView);

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        T item = getItemAtPosition(position);

        getEventBus().post(new Events.ItemSelectionEvent<T>(item));
    }
}
