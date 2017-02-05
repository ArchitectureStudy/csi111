package com.sean.android.mvcsample.splash;

import android.content.Context;
import android.support.annotation.NonNull;

import com.sean.android.github.model.GithubConfiguration;
import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.util.GithubPreferenceKey;
import com.sean.android.mvcsample.base.util.PreferenceKey;
import com.sean.android.mvcsample.base.util.SharedPreferencesService;
import com.sean.android.mvcsample.base.util.StringUtil;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Seonil on 2017-01-20.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    private Context mContext;

    private SharedPreferencesService sharedPreferencesService;

    public SplashPresenter(@NonNull Context context, @NonNull SplashContract.View splashView) {
        mContext = context;
        mView = checkNotNull(splashView);
        mView.setPresenter(this);
        sharedPreferencesService = SharedPreferencesService.getInstance();
    }

    @Override
    public void start() {
        mView.showAnimation();
        mView.setGithubAccessToken(loadGithubAccessToken());
        mView.setGithubId(loadGithubId());
        mView.setGithubRepository(loadGithubRepository());
    }

    public boolean checkGithubId(@NonNull String id) {
        return !StringUtil.isNullOrEmpty(id);
    }

    public boolean checkGithubRepository(@NonNull String repository) {
        return !StringUtil.isNullOrEmpty(repository);
    }

    public boolean checkGithubAccessToken(@NonNull String accessToken) {
        return !StringUtil.isNullOrEmpty(accessToken);
    }

    @Override
    public void openGithubMainPage() {
        if (StringUtil.isNullOrEmpty(mView.getGithubIdText())) {
            mView.showAlertToast(mContext.getString(R.string.alert_message_empty_id));
            return;
        }

        if (StringUtil.isNullOrEmpty(mView.getGithubRepositoryText())) {
            mView.showAlertToast(mContext.getString(R.string.alert_message_empty_repository));
            return;
        }

        if (StringUtil.isNullOrEmpty(mView.getGithubAccessTokenText())) {
            mView.showAlertToast(mContext.getString(R.string.alert_message_empty_accessToken));
        }

        sharedPreferencesService.setPrefData(PreferenceKey.GITHUB_ID, mView.getGithubIdText());
        sharedPreferencesService.setPrefData(PreferenceKey.GITHUB_REPOSITORY, mView.getGithubRepositoryText());
        sharedPreferencesService.setPrefData(PreferenceKey.GITHUB_ACCESS_TOKEN, mView.getGithubAccessTokenText());

        //TODO Setting Github AccessToken
        GithubConfiguration.getInstance().setAccessToken(mView.getGithubAccessTokenText());

        mView.showGithubMainUI();
    }

    @Override
    public String loadGithubId() {
        return sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ID_KEY, mContext.getResources().getString(R.string.default_github_id));
    }

    @Override
    public String loadGithubRepository() {
        return sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_REPOSITORY_KEY, mContext.getResources().getString(R.string.default_github_repository));
    }

    @Override
    public String loadGithubAccessToken() {
        return sharedPreferencesService.getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ACCESS_TOKEN_KEY, mContext.getResources().getString(R.string.github_personal_token));
    }
}
