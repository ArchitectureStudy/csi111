package com.sean.android.vipersample.ui.issuedetail.presenter;

import com.sean.android.vipersample.ui.issuedetail.IssueDetailViewCallbacks;
import com.sean.android.vipersample.ui.issuedetail.interactor.CommentsInteractor;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouter;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;

/**
 * Created by Seonil on 2017-03-06.
 */

public class CommentsPresenter {

    private IssueDetailViewCallbacks mViewCallbacks;

    private CommentRouter mCommentRouter;

    private CommentsInteractor mCommentsInteractor;

    public CommentsPresenter(CommentsInteractor mCommentsInteractor) {
        this.mCommentsInteractor = mCommentsInteractor;
    }

    public void attachView(IssueDetailViewCallbacks issueDetailViewCallbacks) {
        mViewCallbacks = issueDetailViewCallbacks;
    }

    public void attachRouter(CommentRouter commentRouter) {
        mCommentRouter = commentRouter;
    }

    public void detachView() {

    }


    public void onClickedComment(String message) {
        mCommentsInteractor.postComment(message, new CommentsInteractor.ActionPost() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onFailed(String message) {

            }
        });
    }
}
