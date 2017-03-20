package com.sean.android.vipersample.ui.splash.viewmodel;

import android.view.View;

import com.sean.android.vipersample.base.databinding.BindableString;
import com.sean.android.vipersample.base.viewmodel.ViewModel;


/**
 * Created by Seonil on 2017-02-09.
 */

public interface SplashViewModel extends ViewModel {

    BindableString getIdText();

    BindableString getRepositoryText();

    BindableString getAccessTokenText();

    void onClickEnterMainView(View view);
}
