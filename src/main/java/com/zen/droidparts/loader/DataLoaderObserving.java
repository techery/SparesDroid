package com.zen.droidparts.loader;

/**
 * Created by zen on 11/14/13.
 */
public interface DataLoaderObserving {
    public void onStartLoading();
    public void onFinishLoading();
    public void onError();
}
