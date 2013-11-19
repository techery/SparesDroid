package com.zen.droidparts.ui.fragment.loadable.collection;

import com.zen.droidparts.adapter.DataListAdapter;
import com.zen.droidparts.loader.ContentLoader;
import com.zen.droidparts.loader.ContentLoaderController;
import com.zen.droidparts.ui.fragment.loadable.LoadableFragment;
import com.zen.droidparts.ui.view.cell.BaseCell;

public abstract class CollectionFragment<T> extends LoadableFragment<T> {
    protected DataListAdapter<T> dataAdapter;

    @Override
    public void setContentLoader(ContentLoader<T> contentLoader) {
        super.setContentLoader(contentLoader);

        if (getDataAdapter() != null) {
            getDataAdapter().setContentLoader(getContentLoader());
        }

        getContentLoader().load();
    }

    protected ContentLoader<T> createLoaderWithFactory(ContentLoader.LoaderCreator factory) {
        return new ContentLoaderController<T>(getActivity(), getLoaderManager(), factory);
    }

    protected void setConterLoaderFromLoaderCreator(ContentLoader.LoaderCreator creator) {
        setContentLoader(createLoaderWithFactory(creator));
    }

    public DataListAdapter<T> getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(DataListAdapter<T> dataAdapter) {
        this.dataAdapter = dataAdapter;

        getDataAdapter().setContentLoader(getContentLoader());
        getDataAdapter().getController().setCellBuilder(getCellBuilder());
    }

    protected abstract BaseCell.CellBuilder getCellBuilder();
}
