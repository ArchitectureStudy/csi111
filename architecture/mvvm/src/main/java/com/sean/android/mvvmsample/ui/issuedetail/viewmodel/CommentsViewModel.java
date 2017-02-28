package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import com.sean.android.mvvmsample.base.viewmodel.NotifyUpdateViewModelListener;

/**
 * Created by Seonil on 2017-02-24.
 */

public interface CommentsViewModel {
    void refreshComments();

    void fetchComments();

    boolean showIndicator();

    void setUpdateViewModelListener(NotifyUpdateViewModelListener listener);

}
