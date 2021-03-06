package com.sean.android.vipersample.data;


import com.sean.android.vipersample.base.model.Model;

/**
 * Created by sean on 2017. 1. 21..
 */

public class User extends Model {
    int id;
    String login;
    String avatarUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", login='" + login + '\'' +
                ", id=" + id +
                '}';
    }
}
