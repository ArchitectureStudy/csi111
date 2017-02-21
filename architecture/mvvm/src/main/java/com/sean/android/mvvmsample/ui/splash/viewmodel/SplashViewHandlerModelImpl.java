package com.sean.android.mvvmsample.ui.splash.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import com.sean.android.mvvmsample.ui.issues.IssuesActivity;

/**
 * Created by Seonil on 2017-02-21.
 */

public class SplashViewHandlerModelImpl implements SplashViewHandlerModel {

    private Activity activity;

    public SplashViewHandlerModelImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClickEnterMainView() {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed()) {
                    return;
                }
            }

            activity.startActivity(new Intent(activity, IssuesActivity.class));
            activity.finish();
        }
    }
}
