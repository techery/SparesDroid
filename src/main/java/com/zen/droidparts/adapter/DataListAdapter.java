package com.zen.droidparts.adapter;

import android.widget.ListAdapter;

import com.zen.droidparts.loader.ContentLoader;

/**
 * Created by zen on 11/13/13.
 */
public interface DataListAdapter<T> extends ListAdapter {
    public void setContentLoader(ContentLoader<T> contentLoader);
    public ContentLoader<T> getContentLoader();
    public AdapterController getController();

    public interface Events {
        public class ItemSelectionEvent<T> {
            private final T item;

            public ItemSelectionEvent(T item) {
                this.item = item;
            }

            public T getItem() {
                return item;
            }
        }
    }
}
