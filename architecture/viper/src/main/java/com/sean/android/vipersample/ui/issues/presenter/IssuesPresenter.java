package com.sean.android.vipersample.ui.issues.presenter;

import com.sean.android.vipersample.ui.issues.IssuesViewCallbacks;
import com.sean.android.vipersample.ui.issues.interactor.IssuesInteractor;
import com.sean.android.vipersample.ui.issues.router.IssueRouter;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;

import java.util.List;

/**
 * Created by Seonil on 2017-03-06.
 */

public class IssuesPresenter {

    private IssuesViewCallbacks mViewCallbacks;

    private IssueRouter mIssueRouter;

    private IssuesInteractor mIssuesInteractor;

    public IssuesPresenter(IssuesInteractor mIssuesInteractor) {
        this.mIssuesInteractor = mIssuesInteractor;
    }

    public void refreshIssues() {

    }

    public void attachView(IssuesViewCallbacks issuesViewCallbacks) {
        mViewCallbacks = issuesViewCallbacks;
        fetchIssues();
    }

    public void attachRouter(IssueRouter issueRouter) {
        mIssueRouter = issueRouter;
    }

    public void detachView() {

    }

    public void fetchIssues() {
        mIssuesInteractor.execute(new IssuesInteractor.Action() {
            @Override
            public void onCompleted(List<IssueItemViewModel> itemViewModelList) {
                mViewCallbacks.onNewIssues(itemViewModelList);
            }

            @Override
            public void onFailed(String message) {
                mViewCallbacks.showError(message);
            }
        });
    }

    public void onItemClicked(IssueItemViewModel issueItemViewModel) {
        mIssueRouter.navigateIssueDetail(issueItemViewModel);
    }

}
