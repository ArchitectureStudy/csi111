package com.sean.android.mvvmsample;

import android.app.Application;

import com.sean.android.mvvmsample.base.util.SharedPreferencesService;


/**
 * Created by Seonil on 2017-01-20.
 */

public class GithubApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesService.getInstance().load(getApplicationContext());
    }
}
