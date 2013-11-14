package com.zen.droidparts.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.zen.droidparts.loader.DataController;
import com.zen.droidparts.ui.view.cell.BaseCell;

import java.util.List;

import de.greenrobot.event.EventBus;

public class BaseArrayListAdapter<T> extends ArrayAdapter<T> implements
        DataController.DataControllerCallBack<List<T>>,
        DataListAdapter<List<T>>
{
    private AdapterController adapterController;
    private DataController<List<T>> dataController;

    public BaseArrayListAdapter(Context context) {
        this(context, null);
    }

    public BaseArrayListAdapter(Context context, BaseCell.CellBuilder<T> cellBuilder) {
        this(context, cellBuilder, null);
    }

    public BaseArrayListAdapter(Context context, BaseCell.CellBuilder<T> cellBuilder, EventBus bus) {
        super(context, -1);
        this.adapterController = new AdapterController(context, cellBuilder, bus);
    }

    @Override
    public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {
        android.view.View cell = convertView;

        if (cell == null) {
            cell = this.adapterController.newView(this, position, parent);
        }

        return this.adapterController.bindView(this, position, cell);
    }

    public AdapterController getController() {
        return adapterController;
    }

    public void setDataController(DataController<List<T>> dataController) {
        this.dataController = dataController;
        if (this.dataController != null) {
            this.dataController.setDataControllerCallBack(this);
        }
    }

    public DataController<List<T>> getDataController() {
        return dataController;
    }

    @Override
    public void processResult(List<T> result) {
        for (T res : result) {
            this.add(res);
        }

        this.notifyDataSetChanged();
    }

    @Override
    public void processError(Throwable throwable) {

    }
}