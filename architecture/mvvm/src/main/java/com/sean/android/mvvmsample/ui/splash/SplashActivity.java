package com.sean.android.mvvmsample.ui.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewHandlerModel;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewHandlerModelImpl;

public class SplashActivity extends AppCompatActivity {

    SplashViewHandlerModel splashViewHandlerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashViewHandlerModel = new SplashViewHandlerModelImpl(this);


        setContentView(R.layout.spalsh_activity);
    }
}
