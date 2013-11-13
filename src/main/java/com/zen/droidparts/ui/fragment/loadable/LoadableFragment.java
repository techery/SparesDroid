package com.zen.droidparts.ui.fragment.loadable;

import com.zen.droidparts.loader.DataController;
import com.zen.droidparts.ui.fragment.BaseFragment;


public abstract class LoadableFragment<T> extends BaseFragment implements DataController.DataControllerCallBack<T> {
    private DataController<T> dataController;

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    private void load() {
        dataController = new DataController<T>(getActivity(), getLoaderManager(), getLoaderFactory());
        dataController.setDataControllerCallBack(this);
        dataController.load();
    }

    protected void reload() {
        dataController.reload();
    }

    protected abstract DataController.LoaderFactory getLoaderFactory();

    public abstract void processResult(T result);
    public abstract void processError(Throwable throwable);
}
