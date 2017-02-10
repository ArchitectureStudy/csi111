package com.sean.android.mvvmsample.ui.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewModel;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewModelImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements OnSplashActionListener {

    @BindView(R.id.splash_main_view)
    SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh_activity);
        ButterKnife.bind(this);
        splashView.setOnSplashActionListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClickActivityFinish(View view) {
        finish();
    }
}
