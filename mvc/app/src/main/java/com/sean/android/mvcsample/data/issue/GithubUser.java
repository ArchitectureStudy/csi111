package com.sean.android.mvcsample.data.issue;

/**
 * Created by sean on 2017. 1. 7..
 */

public class GithubUser {
    private String user;
    private String repository;

    public GithubUser() {
    }

    public GithubUser(String user, String repository) {
        this.user = user;
        this.repository = repository;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }


    public boolean validateGitData(){
        return user != null && user.length() > 0 && repository != null && repository.length() > 0;
    }

}
