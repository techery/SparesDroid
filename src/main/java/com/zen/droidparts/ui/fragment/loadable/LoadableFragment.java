package com.zen.droidparts.ui.fragment.loadable;

import android.view.View;

import com.zen.droidparts.loader.DataController;
import com.zen.droidparts.ui.fragment.BaseFragment;


public abstract class LoadableFragment<T> extends BaseFragment implements DataController.DataControllerCallBack<T> {
    private DataController<T> dataController;

    @Override
    protected void afterCreateView(View rootView) {
        super.afterCreateView(rootView);
        setupDataController();
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    private void load() {
        dataController.load();
    }

    protected void setupDataController() {
        setDataController(new DataController<T>(getActivity(), getLoaderManager(), getLoaderFactory()));
        getDataController().setDataControllerCallBack(this);
    }

    protected void reload() {
        getDataController().reload();
    }

    protected abstract DataController.LoaderFactory getLoaderFactory();

    public void processResult(T result) {

    }

    public void processError(Throwable throwable) {

    }

    public DataController<T> getDataController() {
        return dataController;
    }

    public void setDataController(DataController<T> dataController) {
        this.dataController = dataController;
    }


}
