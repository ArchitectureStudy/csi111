package com.sean.android.vipersample.ui.issuedetail.interactor;

import com.sean.android.vipersample.data.comment.Comment;
import com.sean.android.vipersample.data.comment.Comments;
import com.sean.android.vipersample.data.comment.CommentsDataSource;
import com.sean.android.vipersample.data.comment.CommentsRepository;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModelImpl;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seonil on 2017-03-06.
 */

public class CommentsInteractor {

    private final int mIssueNumber;

    private String mIssueTitle;

    private String mIssueBody;

    private IssueDetailViewModel mIssueDetailViewModel;

    public CommentsInteractor(int issueNumber) {
        this.mIssueNumber = issueNumber;
        this.mIssueDetailViewModel = new IssueDetailViewModelImpl();
    }

    public IssueDetailViewModel getIssueDetailViewModel() {
        return mIssueDetailViewModel;
    }

    public void execute(final Action action) {
        CommentsRepository.getInstance().getComments(mIssueNumber, new CommentsDataSource.LoadCommentsCallback() {
            @Override
            public void onCommentsLoaded(Comments comments) {
                List<CommentItemViewModel> itemVMList = new ArrayList<>();
                for (Comment comment : comments.getModels()) {
                    itemVMList.add(new CommentItemViewModelImpl(comment));
                }

                if (action != null) {
                    action.onCompleted(itemVMList);
                }

            }

            @Override
            public void onCommentsFailed(int code, String message) {
                if (action != null) {
                    action.onFailed(message);
                }
            }
        });
    }

    public void postComment(String comment, final ActionPost actionPost) {
        CommentsRepository.getInstance().createComment(mIssueNumber, comment, new CommentsDataSource.PostCommentCallback() {
            @Override
            public void onCommentPosted(Comment comment) {
                if (actionPost != null) {
                    actionPost.onCompleted();
                }
            }

            @Override
            public void onCommentPostFailed(int code, String message) {
                if (actionPost != null) {
                    actionPost.onFailed(message);
                }
            }
        });
    }

    public void setIssueTitle(String mIssueTitle) {
        this.mIssueTitle = mIssueTitle;
        mIssueDetailViewModel.getTitleText().set(mIssueTitle);
    }

    public void setIssueBody(String mIssueBody) {
        this.mIssueBody = mIssueBody;
        mIssueDetailViewModel.getContentsText().set(mIssueBody);
    }

    public interface Action {
        void onCompleted(List<CommentItemViewModel> itemViewModelList);

        void onFailed(String message);
    }

    public interface ActionPost {
        void onCompleted();

        void onFailed(String message);
    }
}
