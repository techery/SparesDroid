package com.zen.droidparts.ui.fragment.loadable.collection;

import android.content.Context;
import android.os.Bundle;

import com.zen.droidparts.ui.fragment.loadable.LoadableFragment;

import de.greenrobot.event.EventBus;

public abstract class CollectionFragment<T> extends LoadableFragment<T> {

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

    public interface BaseCell<T> {
        public void fillWithItem(T item);
        public void prepareForReuse();
        public void setEventBus(EventBus eventBus);
        public void saveState(Bundle b);
        public void restoreState(Bundle bundle);

        public int getLastPosition();
        public void setLastPosition(int position);

        public interface CellBuilder<T> {
            BaseCell build(Context c, T item);
        }
    }


}
