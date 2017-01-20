package com.sean.android.mvcsample.issuedetail;

import com.sean.android.mvcsample.base.BasePresenter;
import com.sean.android.mvcsample.base.BaseView;
import com.sean.android.mvcsample.data.issue.Issue;

import java.util.List;

/**
 * Created by Seonil on 2017-01-16.
 */

public interface IssueDetailContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void loadIssue(boolean forceUpdate);

        void clearIssues();

        void openIssueDetail(Issue issue);
    }
}
