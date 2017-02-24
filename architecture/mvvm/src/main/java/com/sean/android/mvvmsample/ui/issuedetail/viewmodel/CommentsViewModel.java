package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import java.util.List;

import rx.Observable;

/**
 * Created by Seonil on 2017-02-24.
 */

public interface CommentsViewModel {
    void refreshComments();

    void fetchComments();

    boolean showIndicator();

    Observable<List<CommentItemViewModel>> observComments();

}
