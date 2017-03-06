package com.sean.android.vipersample.ui.issues;

import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;

import java.util.Collection;

/**
 * Created by Seonil on 2017-03-06.
 */

public interface IssuesViewCallbacks {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void onNewIssues(Collection<IssueItemViewModel> itemViewModelCollection);

}
