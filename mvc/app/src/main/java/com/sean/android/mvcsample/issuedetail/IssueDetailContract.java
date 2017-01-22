package com.sean.android.mvcsample.issuedetail;

import com.sean.android.mvcsample.base.BasePresenter;
import com.sean.android.mvcsample.base.BaseView;
import com.sean.android.mvcsample.data.comment.Comment;
import com.sean.android.mvcsample.data.issue.Issue;

import java.util.List;

/**
 * Created by Seonil on 2017-01-16.
 */

public interface IssueDetailContract {

    interface View extends BaseView<Presenter> {
        void showIssueDetail(String title, String body);

        void showComments(List<Comment> commentList);

    }

    interface Presenter extends BasePresenter {
        void loadIssue(boolean forceUpdate);

        void loadComments(boolean forceUpdate);

        void refreshIssue(boolean forceUpdate);

        void refreshComments(boolean forceUpdate);

        void postComment(String text);


    }
}
