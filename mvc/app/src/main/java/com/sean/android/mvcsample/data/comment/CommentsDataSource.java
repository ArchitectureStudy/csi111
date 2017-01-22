package com.sean.android.mvcsample.data.comment;

import android.support.annotation.NonNull;

/**
 * Created by sean on 2017. 1. 21..
 */

public interface CommentsDataSource {

    interface LoadCommentsCallback {
        void onCommentsLoaded(Comments comments);

        void onCommentsFailed();
    }

    interface PostCommentCallback {
        void onCommentPosted(Comment comment);

        void onCommentPostFailed();

    }

    void getComments(int issueNumber, @NonNull LoadCommentsCallback callback);

    void postComments(int number, String body, PostCommentCallback callback);
}
