package com.sean.android.vipersample.data.issue;

import android.support.annotation.NonNull;

/**
 * Created by Seonil on 2017-01-16.
 */

public interface IssuesDataSource {

    interface LoadIssuesCallback {
        void onIssuesLoaded(Issues issues);

        void onIssuesFailed(int code, String message);
    }

    interface LoadIssueCallback {
        void onIssueLoaded(Issue issue);

        void onIssueFailed(int code, String message);
    }

    void fetchIssues(@NonNull LoadIssuesCallback callback);

    void fetchIssues();

    Issues getIssues();

    void fetchIssue(int issueNumber, LoadIssueCallback callback);

    void fetchIssue(int issueNumber);

    Issue getIssue(int issueNumber);

}
