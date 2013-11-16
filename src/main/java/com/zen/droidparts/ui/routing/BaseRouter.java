package com.zen.droidparts.ui.routing;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by zen on 11/16/13.
 */
public class BaseRouter {
    private Activity activity;

    protected void startActivityOfClassWithoutParams(Class<?> activityClass) {
        Intent intent = new Intent(this.activity, activityClass);
        this.activity.startActivity(intent);
    }

    public void andFinish() {
        this.activity.finish();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
