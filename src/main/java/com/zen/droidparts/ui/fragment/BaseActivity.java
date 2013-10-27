package com.zen.droidparts.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.Views;

/**
 * Created by zen on 10/27/13.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int contentResourse = getContentViewResource();
        if (contentResourse > 0) {
            setContentView(contentResourse);
        }

        Views.inject(this);

        afterCreateView(savedInstanceState);
    }

    protected abstract int getContentViewResource();

    protected abstract void afterCreateView(Bundle savedInstanceState);
}
