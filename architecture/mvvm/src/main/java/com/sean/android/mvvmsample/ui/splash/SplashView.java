package com.sean.android.mvvmsample.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.databinding.SplashViewBinding;
import com.sean.android.mvvmsample.ui.issues.IssuesActivity;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewModel;
import com.sean.android.mvvmsample.ui.splash.viewmodel.SplashViewModelImpl;

import butterknife.ButterKnife;

/**
 * Created by Seonil on 2017-01-20.
 */

public class SplashView extends RelativeLayout {

    private SplashViewBinding mSplashViewBinding;

    private SplashViewModel mSplashViewModel;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initDataBinding();
        showAnimation();
    }

    private void initDataBinding() {
        mSplashViewModel = new SplashViewModelImpl(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSplashViewBinding = DataBindingUtil.inflate(inflater, R.layout.splash_view, this, true);
        ButterKnife.bind(this);

        mSplashViewBinding.setSplashViewModel(mSplashViewModel);
    }


    public void showAnimation() {

        mSplashViewBinding.titleTextView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(800).alpha(1.0f).translationY(-700).setInterpolator(new AccelerateInterpolator()).start();
        mSplashViewBinding.inputGithubIdLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mSplashViewBinding.inputGithubRepoLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mSplashViewBinding.inputGithubAccessTokenLayout.animate().setDuration(1000).alpha(1.0f).setStartDelay(300).start();
        mSplashViewBinding.enterMainPageButton.animate().scaleY(1.0f).scaleX(1.0f).setDuration(1000).alpha(1.0f).start();
    }

//    public String getGithubIdText() {
//        return mSplashViewBinding.inputGithubIdEditText.getText().toString();
//    }
//
//    public String getGithubRepositoryText() {
//        return mGithubRepoInputText.getText().toString();
//    }
//
//    public String getGithubAccessTokenText() {
//        return mGithubAccessTokenInputText.getText().toString();
//    }

    public void showGithubMainUI() {
        getContext().startActivity(new Intent(getContext(), IssuesActivity.class));
    }

    public void showAlertToast(String toastMessage) {
        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
    }

    public void onClickMoveMain(View view) {
        Log.d("TEST", "onClickMovieMain");
    }
}
