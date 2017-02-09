package com.sean.android.mvpsample.data.comment;

import android.support.annotation.NonNull;

/**
 * Created by sean on 2017. 1. 21..
 */

public interface CommentsDataSource {

    interface LoadCommentsCallback {
        void onCommentsLoaded(Comments comments);

        void onCommentsFailed(int code, String message);
    }

    interface PostCommentCallback {
        void onCommentPosted(Comment comment);

        void onCommentPostFailed(int code, String message);

    }

    void getComments(int issueNumber, @NonNull LoadCommentsCallback callback);

    void createComment(int number, String body, PostCommentCallback callback);
}
