package com.techery.spares.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techery.spares.annotations.Layout;

import butterknife.Views;

public class AdapterHelper {
    private final LayoutInflater layoutInflater;

    public AdapterHelper(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public <T> View buildCell(Class<T> cellClass, ViewGroup parent) {
        Layout layoutAnnotation = cellClass.getAnnotation(Layout.class);

        View cellView = layoutInflater.inflate(layoutAnnotation.value(), parent, false);

        Cell<T> cellObject = null;

        try {
            cellObject = (Cell<T>) cellClass.newInstance();
            Views.inject(cellObject, cellView);

            if (cellObject instanceof Cell.OnReadyListener) {
                ((Cell.OnReadyListener)cellObject).onViewReady();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        cellView.setTag(cellObject);

        return cellView;
    }
}
