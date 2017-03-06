package com.sean.android.vipersample.ui.issuedetail;

import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;

import java.util.Collection;

/**
 * Created by Seonil on 2017-03-06.
 */

public interface IssueDetailViewCallbacks {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void onNewComments(Collection<CommentItemViewModel> itemViewModelCollection);

    void onNewIssueDetail(IssueDetailViewModel issueDetailViewModel);
}
