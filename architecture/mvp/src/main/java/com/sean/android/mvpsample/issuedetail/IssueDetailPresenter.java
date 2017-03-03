package com.sean.android.mvpsample.issuedetail;

import android.support.annotation.NonNull;
import android.util.Log;

import com.sean.android.mvpsample.data.comment.Comment;
import com.sean.android.mvpsample.data.comment.Comments;
import com.sean.android.mvpsample.data.comment.CommentsDataSource;
import com.sean.android.mvpsample.data.comment.CommentsRepository;
import com.sean.android.mvpsample.data.issue.Issue;
import com.sean.android.mvpsample.data.issue.IssuesDataSource;
import com.sean.android.mvpsample.data.issue.IssuesRepository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssueDetailPresenter implements IssueDetailContract.Presenter {

    private final IssuesRepository mIssuesRepository;

    private final CommentsRepository mCommentsRepository;

    private IssueDetailContract.View mIssueDetailView;

    private boolean mFirstLoad = true;

    private int mIssueNumber;

    private String mIssueTitle;

    private String mIssueBody;

    public IssueDetailPresenter(int issueNumber, String issueTitle, String issueBody, @NonNull CommentsRepository commentsRepository, @NonNull IssuesRepository issuesRepository, @NonNull IssueDetailContract.View issueDetailView) {
        mIssueNumber = issueNumber;
        mIssueTitle = issueTitle;
        mIssueBody = issueBody;
        mIssuesRepository = checkNotNull(issuesRepository);
        mCommentsRepository = checkNotNull(commentsRepository);
        mIssueDetailView = checkNotNull(issueDetailView);
        mIssueDetailView.setPresenter(this);
    }

    @Override
    public void loadIssue(boolean forceUpdate) {
        mIssuesRepository.getIssue(mIssueNumber, new IssuesDataSource.LoadIssueCallback() {
            @Override
            public void onIssueLoaded(Issue issue) {
                //TODO
            }

            @Override
            public void onIssueFailed(int code, String message) {
                Log.d("TEST", "code =[" + code +"] message =[" + message+"]");
            }
        });
    }

    @Override
    public void loadComments(boolean forceUpdate) {
        mCommentsRepository.getComments(mIssueNumber, new CommentsDataSource.LoadCommentsCallback() {
            @Override
            public void onCommentsLoaded(Comments comments) {
                mIssueDetailView.showComments(comments.getModels());
            }

            @Override
            public void onCommentsFailed(int code, String message) {
                Log.d("TEST", "code =[" + code +"] message =[" + message+"]");
            }
        });
    }

    @Override
    public void refreshIssue(boolean forceUpdate) {

    }

    @Override
    public void refreshComments(boolean forceUpdate) {

    }

    @Override
    public void postComment(String text) {
        mCommentsRepository.createComment(mIssueNumber, text, new CommentsDataSource.PostCommentCallback() {
            @Override
            public void onCommentPosted(Comment comment) {
                loadComments(true);
            }

            @Override
            public void onCommentPostFailed(int code, String message) {
                Log.d("TEST", "code =[" + code +"] message =[" + message+"]");
            }
        });
    }

    @Override
    public void start() {
        mIssueDetailView.showIssueDetail(mIssueTitle, mIssueBody);
        loadIssue(true);
        loadComments(true);
    }
}
