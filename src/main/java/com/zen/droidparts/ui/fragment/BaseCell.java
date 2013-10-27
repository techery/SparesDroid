package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.widget.LinearLayout;

import de.greenrobot.event.EventBus;

public abstract class BaseCell<T> extends LinearLayout implements CollectionFragment.BaseCell<T> {
    private T modelObject;

    private EventBus eventBus;

    public BaseCell(Context context) {
        super(context);
    }

    @Override
    public void setEventBus(EventBus bus) {
        this.eventBus = bus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public T getModelObject() {
        return modelObject;
    }

    public void setModelObject(T modelObject) {
        this.modelObject = modelObject;
    }

    @Override
    public void fillWithItem(T item) {
        setModelObject(item);
        syncUIStateWithModel();
    }

    protected abstract void syncUIStateWithModel();
}
