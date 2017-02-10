package com.sean.android.mvvmsample.ui.splash.viewmodel;

import android.view.View;

/**
 * Created by Seonil on 2017-02-09.
 */

public interface SplashViewModel {

    String getIdText();

    String getRepositoryText();

    String getAccessTokenText();

    void onClickMoveMain(View view);

}
