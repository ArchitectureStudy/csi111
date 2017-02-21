package com.sean.android.mvvmsample.ui.splash.viewmodel;

import android.content.Context;
import android.view.View;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.base.databinding.BindableString;
import com.sean.android.mvvmsample.base.util.GithubPreferenceKey;
import com.sean.android.mvvmsample.base.util.SharedPreferencesService;
import com.sean.android.mvvmsample.base.util.StringUtil;

import rx.Subscription;

/**
 * Created by Seonil on 2017-02-10.
 * <p>
 * Reference https://github.com/coding-jam/databinding.git
 * https://github.com/erikcaffrey/People-MVVM.git
 */

public class SplashViewModelImpl implements SplashViewModel {

    private SharedPreferencesService sharedPreferencesService;
    private Context mContext;

    private BindableString idText = new BindableString();
    private BindableString repoText = new BindableString();
    private BindableString accessTokenText = new BindableString();

    private Subscription subscription;

    public SplashViewModelImpl(Context context) {
        this.mContext = context;
        initData();

    }

    private void initData() {
        sharedPreferencesService = SharedPreferencesService.getInstance();
        idText.set(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ID_KEY, mContext.getResources().getString(R.string.default_github_id)));
        repoText.set(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_REPOSITORY_KEY, mContext.getResources().getString(R.string.default_github_repository)));
        accessTokenText.set(sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ACCESS_TOKEN_KEY, mContext.getResources().getString(R.string.github_personal_token)));
    }

    @Override
    public BindableString getIdText() {
        return idText;
    }

    @Override
    public BindableString getRepositoryText() {
        return repoText;
    }

    @Override
    public BindableString getAccessTokenText() {
        return accessTokenText;
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
