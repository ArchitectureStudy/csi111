package com.sean.android.mvcsample.data.issue;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sean.android.mvcsample.base.network.HttpResponseData;
import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.data.dto.IssueDTO;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesRepository implements IssuesDataSource {
    private static IssuesRepository instance = null;

    private GetIssuesFromGithubServiceWorker mGetIssuesFromGithubServiceWorker;

    private Issues mCachedIssues;

    private boolean mCacheIsDirty = false;

    private IssuesRepository() {
        GithubUser githubUser = createDummyData();
        mGetIssuesFromGithubServiceWorker = new GetIssuesFromGithubServiceWorker(githubUser);
    }

    public static IssuesRepository getInstance() {
        if (instance == null) {
            instance = new IssuesRepository();
        }
        return instance;
    }

    @Override
    public void getIssues(@NonNull LoadIssuesCallback callback) {
        checkNotNull(callback);

        if (mCachedIssues != null && !mCacheIsDirty) {
            callback.onIssuesLoaded(mCachedIssues);
            return;
        }

        if (mCacheIsDirty) {
            executeIssuesService(callback);
        }


    }

    @Override
    public void getIssue(@NonNull String issueNumber, LoadIssueCallback callback) {

    }

    @Override
    public void refreshIssues() {
        mCacheIsDirty = true;
    }

    private void refreshCache(Issues issues) {
        if (mCachedIssues == null) {
            mCachedIssues = new Issues();
        }
        mCachedIssues.clear();
        for (Issue issue : issues) {
            mCachedIssues.add(issue);
        }
        mCacheIsDirty = false;
    }


    private GithubUser createDummyData() {
        return new GithubUser("JakeWharton", "DiskLruCache");
    }

    private void executeIssuesService(final LoadIssuesCallback loadIssuesCallback) {
        mGetIssuesFromGithubServiceWorker.setServiceWorkEventListener(new ServiceWorker.ServiceWorkEventListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onComplete(HttpResponseData result) {
                Issues issues = new Issues();
                if (result.getResponseData() instanceof List) {
                    List<IssueDTO> issuesDTO = (List<IssueDTO>) result.getResponseData();
                    for (IssueDTO issueDTO : issuesDTO) {
                        issues.add(Issue.convertModel(issueDTO));
                    }
                }

                refreshCache(issues);
                loadIssuesCallback.onIssuesLoaded(issues);
            }

            @Override
            public void onFail(Throwable e) {
                loadIssuesCallback.onIssuesFailed();
            }
        });
        mGetIssuesFromGithubServiceWorker.executeAsync();
    }
}

