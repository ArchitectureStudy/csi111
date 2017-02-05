package com.sean.android.github.model;

/**
 * Created by Seonil on 2017-01-26.
 */

public class GithubUser {
    private final String userName;

    private final String userRepository;

    public GithubUser(String userName, String userRepository) {
        this.userName = userName;
        this.userRepository = userRepository;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserRepository() {
        return userRepository;
    }
}
