package com.sean.android.mvvmsample.ui.splash.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.base.model.ObservableString;
import com.sean.android.mvvmsample.base.util.GithubPreferenceKey;
import com.sean.android.mvvmsample.base.util.SharedPreferencesService;
import com.sean.android.mvvmsample.base.util.StringUtil;

import java.util.Observable;

/**
 * Created by Seonil on 2017-02-10.
 */

public class SplashViewModelImpl extends Observable implements SplashViewModel {

    private SharedPreferencesService sharedPreferencesService;
    private Context mContext;

    private ObservableField<String> idText;
    private ObservableString repoText;
    private ObservableString accessTokenText;

    public SplashViewModelImpl(Context context) {
        this.mContext = context;
        initData();

    }

    private void initData() {
        sharedPreferencesService = SharedPreferencesService.getInstance();
        idText = new ObservableField<>(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ID_KEY, mContext.getResources().getString(R.string.default_github_id)));
        repoText = new ObservableString(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_REPOSITORY_KEY, mContext.getResources().getString(R.string.default_github_repository)));
        accessTokenText = new ObservableString(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ACCESS_TOKEN_KEY, mContext.getResources().getString(R.string.github_personal_token)));
    }

    @Override
    public String getIdText() {
        return idText.get();
    }

    @Override
    public String getRepositoryText() {
        return repoText.get();
    }

    @Override
    public String getAccessTokenText() {
        return accessTokenText.get();
    }

    @Override
    public void onClickMoveMain(View view) {
        if (StringUtil.isNullOrEmpty(idText.get())) {
            return;
        }

        if (StringUtil.isNullOrEmpty(repoText.get())) {
            return;
        }

        if (StringUtil.isNullOrEmpty(accessTokenText.get())) {
            return;
        }

    }
}
