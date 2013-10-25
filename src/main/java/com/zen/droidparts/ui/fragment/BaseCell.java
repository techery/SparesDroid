package com.zen.droidparts.ui.fragment;

import android.content.Context;
import android.widget.LinearLayout;

import de.greenrobot.event.EventBus;

/**
 * Created by zen on 10/25/13.
 */
public abstract class BaseCell<T> extends LinearLayout implements CollectionFragment.BaseCell<T> {
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
}
