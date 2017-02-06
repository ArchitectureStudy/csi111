package com.sean.android.mvcsample.data.issue;

import android.support.annotation.NonNull;

import com.sean.android.mvcsample.data.issue.Issue;
import com.sean.android.mvcsample.data.issue.Issues;

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

    void getIssues(@NonNull LoadIssuesCallback callback);

    void getIssue(int issueNumber, LoadIssueCallback callback);

    void refreshIssues();


}
