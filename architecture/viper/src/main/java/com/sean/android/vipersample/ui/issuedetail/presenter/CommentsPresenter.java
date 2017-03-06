package com.sean.android.vipersample.ui.issuedetail.presenter;

import com.sean.android.vipersample.ui.issuedetail.IssueDetailViewCallbacks;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouter;
import com.sean.android.vipersample.ui.issues.IssuesViewCallbacks;
import com.sean.android.vipersample.ui.issues.router.IssueRouter;

/**
 * Created by Seonil on 2017-03-06.
 */

public class CommentsPresenter {

    private IssueDetailViewCallbacks mViewCallbacks;

    private CommentRouter mCommentRouter;

    public void attachView(IssueDetailViewCallbacks issueDetailViewCallbacks) {
        mViewCallbacks = issueDetailViewCallbacks;
    }

    public void attachRouter(CommentRouter commentRouter) {
        mCommentRouter = commentRouter;
    }

    public void detachView() {

    }


    public void onClickedComment(String message) {

    }
}
