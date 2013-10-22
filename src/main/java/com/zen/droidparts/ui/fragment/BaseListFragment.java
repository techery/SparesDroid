package com.zen.droidparts.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zen.droidparts.R;

public abstract class BaseListFragment<T> extends ArrayAdapterFragment<T> implements AdapterView.OnItemClickListener {
    protected ListView listView;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);

        setListView(findListView(rootView));

        listView.setOnItemClickListener(this);
    }

    protected ListView findListView(View rootView) {
        return (ListView) rootView.findViewById(R.id.content_list);
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
        this.listView.setAdapter(getArrayAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        T item = getItemAtPosition(position);

        getEventBus().post(new Events.ItemSelectionEvent<T>(item));
    }
}
