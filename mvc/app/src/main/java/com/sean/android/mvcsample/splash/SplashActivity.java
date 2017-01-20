package com.sean.android.mvcsample.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sean.android.mvcsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements OnSplashActionListener {


    SplashPresenter mSplashPresenter;


    @BindView(R.id.splash_main_view)
    SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        splashView.setOnSplashActionListener(this);
        mSplashPresenter = new SplashPresenter(this, splashView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSplashPresenter.start();
    }

    @Override
    public void activityFinish() {
        finish();
    }
}
