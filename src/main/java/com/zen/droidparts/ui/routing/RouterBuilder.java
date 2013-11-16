package com.zen.droidparts.ui.routing;

import android.app.Activity;

/**
 * Created by zen on 11/16/13.
 */
public class RouterBuilder<T extends BaseRouter> {
    private final Class<T> typeClass;

    public RouterBuilder(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public T from(Activity activity) {
        try {
            T router = this.typeClass.newInstance();

            router.setActivity(activity);

            return router;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
