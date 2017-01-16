package com.sean.android.mvcsample.issues;

import com.sean.android.mvcsample.base.BasePresenter;
import com.sean.android.mvcsample.base.BaseView;
import com.sean.android.mvcsample.data.issue.Issue;

import java.util.List;

/**
 * Created by Seonil on 2017-01-16.
 */

public interface IssuesContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showIssues(List<Issue> issueList);

        boolean isActive();

        void showLoadingIssuesError();
    }

    interface Presenter extends BasePresenter {
        void loadIssues(boolean forceUpdate);

        void clearIssues();

        void openIssueDetail(Issue issue);

    }
}
