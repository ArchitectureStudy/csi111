package com.sean.android.mvcsample.splash;

import android.support.annotation.NonNull;

import com.sean.android.mvcsample.base.BasePresenter;
import com.sean.android.mvcsample.base.BaseView;

/**
 * Created by Seonil on 2017-01-20.
 */

public interface SplashContract {
    interface View extends BaseView<SplashContract.Presenter> {
        void showAnimation();

        void setGithubId(@NonNull String id);

        void setGithubRepository(@NonNull String repository);

        void setGithubAccessToken(@NonNull String accessToken);

        String getGithubIdText();

        String getGithubRepositoryText();

        String getGithubAccessTokenText();

        void showGithubMainUI();

        void showAlertToast(String toastMessage);

    }

    interface Presenter extends BasePresenter {
        void openGithubMainPage();

        String loadGithubId();

        String loadGithubRepository();

        String loadGithubAccessToken();

    }

}