package com.sean.android.vipersample.ui.issuedetail;

import com.sean.android.vipersample.ui.issuedetail.interactor.CommentsInteractor;
import com.sean.android.vipersample.ui.issuedetail.presenter.IssueDetailPresenter;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouter;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouterImpl;

/**
 * Created by Seonil on 2017-03-06.
 */

final class IssueDetailModule {
    private IssueDetailActivity mIssueDetailActivity;


    IssueDetailModule(IssueDetailActivity issueDetailActivity) {
        this.mIssueDetailActivity = issueDetailActivity;
    }

    CommentRouter getRouter() {
        return new CommentRouterImpl(mIssueDetailActivity);
    }

    static CommentsInteractor createIssueInteractor(int issueNumber) {
        return new CommentsInteractor(issueNumber);
    }

    static IssueDetailPresenter createIssuePresenter(CommentsInteractor commentsInteractor) {
        return new IssueDetailPresenter(commentsInteractor);
    }

}
