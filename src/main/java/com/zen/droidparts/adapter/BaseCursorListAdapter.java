package com.zen.droidparts.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.zen.droidparts.loader.DataController;
import com.zen.droidparts.ui.view.cell.BaseCell;

import de.greenrobot.event.EventBus;

public class BaseCursorListAdapter extends CursorAdapter implements DataListAdapter<Cursor>,DataController.DataControllerCallBack<Cursor> {
    private DataController<Cursor> dataController;
    private AdapterController adapterController;

    public BaseCursorListAdapter(Context context) {
        this(context, null, null);
    }

    public BaseCursorListAdapter(Context context, BaseCell.CellBuilder cellBuilder) {
        this(context, cellBuilder, null);
    }

    public BaseCursorListAdapter(Context context, BaseCell.CellBuilder cellBuilder, EventBus bus) {
        super(context, null, false);
        this.adapterController = new AdapterController(context, cellBuilder, bus);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return this.adapterController.newView(this, cursor.getPosition(), parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        this.adapterController.bindView(this, cursor.getPosition(), view);
    }

    @Override
    public void setDataController(DataController<Cursor> dataController) {
        this.dataController = dataController;
        this.dataController.setDataControllerCallBack(this);
    }

    @Override
    public DataController<Cursor> getDataController() {
        return this.dataController;
    }

    @Override
    public AdapterController getController() {
        return this.adapterController;
    }

    @Override
    public void processResult(Cursor result) {
        result.moveToPosition(-1);
        result.moveToNext();
        swapCursor(result);
        notifyDataSetChanged();
    }

    @Override
    public void processError(Throwable throwable) {

    }


}
