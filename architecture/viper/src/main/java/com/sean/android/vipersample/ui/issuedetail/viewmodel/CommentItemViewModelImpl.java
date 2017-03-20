package com.sean.android.vipersample.ui.issuedetail.viewmodel;

import com.sean.android.vipersample.data.comment.Comment;

/**
 * Created by Seonil on 2017-02-27.
 */

public class CommentItemViewModelImpl implements CommentItemViewModel {

    private Comment mComment;

    public CommentItemViewModelImpl(Comment mComment) {
        this.mComment = mComment;
    }

    @Override
    public String getCommentAuthorIdText() {
        return mComment.getUser().getLogin();
    }

    @Override
    public String getCommentText() {
        return mComment.getBody();
    }
}
