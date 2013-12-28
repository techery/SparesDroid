package com.techery.spares.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import dagger.ObjectGraph;


public class BaseArrayAdapter<T> extends ArrayAdapter<T> {

    private final ObjectGraph objectGraph;
    private final AdapterHelper adapterHelper;
    private final Class<? extends Cell<T>> cellClass;

    public BaseArrayAdapter(Context context, ObjectGraph objectGraph, List<T> objects, Class<? extends Cell<T>> cellClass) {
        super(context, -1, objects);
        this.objectGraph = objectGraph;
        this.cellClass = cellClass;
        adapterHelper = new AdapterHelper((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = adapterHelper.buildCell(cellClass, parent);
        }

        Cell<T> cell = (Cell<T>) convertView.getTag();

        objectGraph.inject(cell);

        cell.setObject(getItem(position));

        return convertView;
    }

}
