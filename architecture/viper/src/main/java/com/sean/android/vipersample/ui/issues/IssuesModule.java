package com.sean.android.vipersample.ui.issues;

import com.sean.android.vipersample.ui.issues.interactor.IssuesInteractor;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenter;
import com.sean.android.vipersample.ui.issues.router.IssueRouter;
import com.sean.android.vipersample.ui.issues.router.IssueRouterImpl;

/**
 * Created by Seonil on 2017-03-06.
 */

public final class IssuesModule {
    private IssuesActivity issuesActivity;


    public IssuesModule(IssuesActivity issuesActivity) {
        this.issuesActivity = issuesActivity;
    }

    public IssueRouter getRouter() {
        return new IssueRouterImpl(issuesActivity);
    }

    static IssuesInteractor createIssueInteractor() {
        return new IssuesInteractor();
    }

    static IssuesPresenter createIssuePresenter(IssuesInteractor issuesInteractor) {
        return new IssuesPresenter(issuesInteractor);
    }


}
