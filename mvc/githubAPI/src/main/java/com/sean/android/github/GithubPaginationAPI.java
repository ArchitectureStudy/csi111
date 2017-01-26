package com.sean.android.github;

import com.sean.android.github.call.GithubAPICallService;

import java.util.List;

/**
 * Created by Seonil on 2017-01-24.
 */

public abstract class GithubPaginationAPI<T extends GithubAPICallService, E> extends GithubAPI<T> implements PaginationAPI<E> {

    private String mNextPageUrl;

    public GithubPaginationAPI(Class clazz) {
        super(clazz);
    }

    protected void setNextPageUrl(String nextPageUrl) {
        this.mNextPageUrl = nextPageUrl;
    }

    public String getNextPageUrl() {
        return mNextPageUrl;
    }
}
