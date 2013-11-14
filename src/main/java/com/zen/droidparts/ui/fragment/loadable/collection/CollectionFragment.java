package com.zen.droidparts.ui.fragment.loadable.collection;

import com.zen.droidparts.adapter.DataListAdapter;
import com.zen.droidparts.ui.fragment.loadable.LoadableFragment;
import com.zen.droidparts.ui.view.cell.BaseCell;

import de.greenrobot.event.EventBus;

public abstract class CollectionFragment<T> extends LoadableFragment<T> {
    protected DataListAdapter<T> dataAdapter;

    @Override
    public void setEventBus(EventBus eventBus) {
        super.setEventBus(eventBus);
        if (getDataAdapter() != null) {
            getDataAdapter().getController().setEventBus(getEventBus());
        }
    }

    protected abstract BaseCell.CellBuilder getCellBuilder();

    public DataListAdapter<T> getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(DataListAdapter<T> dataAdapter) {
        this.dataAdapter = dataAdapter;

        getDataAdapter().getController().setEventBus(getEventBus());
        getDataAdapter().getController().setCellBuilder(getCellBuilder());
    }
}
