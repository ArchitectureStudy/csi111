package com.sean.android.mvcsample.data.issue;

import android.support.annotation.NonNull;

import com.sean.android.mvcsample.base.network.HttpResponseData;
import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.base.util.GithubPreferenceKey;
import com.sean.android.mvcsample.base.util.SharedPreferencesService;
import com.sean.android.mvcsample.data.comment.GetIssueCommentsFromGithubServiceWorker;
import com.sean.android.mvcsample.data.dto.IssueDTO;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesRepository implements IssuesDataSource {
    private static IssuesRepository instance = null;

    private GetIssuesFromGithubServiceWorker mGetIssuesFromGithubServiceWorker;

    private GetIssueFromGithubServiceWorker mGetIssueFromGithubServiceWorker;

    private Issues mCachedIssues;

    private boolean mCacheIsDirty = false;

    private IssuesRepository() {
        this(new GithubUser(SharedPreferencesService.getInstance().getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ID_KEY), SharedPreferencesService.getInstance().getPrefStringData(GithubPreferenceKey.PREF_GITHUB_REPOSITORY_KEY)));
    }

    private IssuesRepository(GithubUser githubUser) {
        mGetIssuesFromGithubServiceWorker = new GetIssuesFromGithubServiceWorker(githubUser);
        mGetIssueFromGithubServiceWorker = new GetIssueFromGithubServiceWorker(githubUser);
    }

    public static IssuesRepository getInstance() {
        if (instance == null) {
            instance = new IssuesRepository();
        }
        return instance;
    }

    public static IssuesRepository getInstance(GithubUser gitHubUser) {
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
    public void getIssue(int issueNumber, LoadIssueCallback callback) {
        checkNotNull(callback);

        if (issueNumber < 0) {
            callback.onIssueFailed();
            return;
        }

        executeIssueService(issueNumber, callback);

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

    private void executeIssueService(int number, final LoadIssueCallback loadIssuesCallback) {
        mGetIssueFromGithubServiceWorker.setServiceWorkEventListener(new ServiceWorker.ServiceWorkEventListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onComplete(HttpResponseData result) {
                Issue issue = new Issue();
                if (result.getResponseData() instanceof IssueDTO) {
                    issue = Issue.convertModel((IssueDTO) result.getResponseData());
                }

                loadIssuesCallback.onIssueLoaded(issue);
            }

            @Override
            public void onFail(Throwable e) {
                loadIssuesCallback.onIssueFailed();
            }
        });

        mGetIssuesFromGithubServiceWorker.executeAsync();
    }
}

