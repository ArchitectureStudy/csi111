package com.sean.android.github.model;


/**
 * Created by Seonil on 2017-01-26.
 */

public class GithubConfiguration {
    private volatile static GithubConfiguration instance = null;

    private String mAccessToken;

    private GithubConfiguration() {
    }

    public static GithubConfiguration getInstance() {
        if (instance == null) {
            synchronized (GithubConfiguration.class) {
                if (instance == null) {
                    instance = new GithubConfiguration();
                }
            }
        }
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    @Header("Authorization")
    public String getAccessToken() {
        return mAccessToken;
    }
}
