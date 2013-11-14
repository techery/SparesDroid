package com.zen.droidparts.loader;

import android.database.Observable;

public class DataLoaderObserver extends Observable<DataLoaderObserving> {
    public void sendOnStartLoading() {
        synchronized (mObservers) {
            for(DataLoaderObserving observing : mObservers) {
                observing.onStartLoading();
            }
        }
    }

    public void sendOnFinishLoading() {
        synchronized (mObservers) {
            for(DataLoaderObserving observing : mObservers) {
                observing.onFinishLoading();
            }
        }
    }

    public void sendOnError() {
        synchronized (mObservers) {
            for(DataLoaderObserving observing : mObservers) {
                observing.onError();
            }
        }
    }
}
