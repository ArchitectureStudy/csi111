package com.sean.android.vipersample.ui.issuedetail.presenter;

import com.sean.android.vipersample.ui.issuedetail.IssueDetailViewCallbacks;
import com.sean.android.vipersample.ui.issuedetail.interactor.CommentsInteractor;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouter;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;

import java.util.List;

/**
 * Created by Seonil on 2017-03-06.
 */

public class IssueDetailPresenter {

    private IssueDetailViewCallbacks mViewCallbacks;

    private CommentRouter mCommentRouter;

    private CommentsInteractor mCommentsInteractor;

    public IssueDetailPresenter(CommentsInteractor mCommentsInteractor) {
        this.mCommentsInteractor = mCommentsInteractor;
    }

    public void attachView(IssueDetailViewCallbacks issueDetailViewCallbacks) {
        mViewCallbacks = issueDetailViewCallbacks;
        fetchComments();
    }

    public void attachRouter(CommentRouter commentRouter) {
        mCommentRouter = commentRouter;
    }

    public void detachView() {

    }

    public IssueDetailViewModel getIssueDetailViewModel() {
        return mCommentsInteractor.getIssueDetailViewModel();
    }

    public void fetchComments() {
        mCommentsInteractor.execute(new CommentsInteractor.Action() {
            @Override
            public void onCompleted(List<CommentItemViewModel> itemViewModelList) {
                mViewCallbacks.onNewComments(itemViewModelList);
            }

            @Override
            public void onFailed(String message) {
                mViewCallbacks.showError(message);
            }
        });
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
