package com.sean.android.mvvmsample.ui.splash.viewmodel;

import android.view.View;

import com.sean.android.mvvmsample.base.databinding.BindableString;

/**
 * Created by Seonil on 2017-02-09.
 */

public interface SplashViewModel {

    BindableString getIdText();

    BindableString getRepositoryText();

    BindableString getAccessTokenText();

    void onClickMoveMain(View view);

}
