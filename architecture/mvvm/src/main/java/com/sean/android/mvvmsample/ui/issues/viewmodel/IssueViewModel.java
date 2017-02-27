package com.sean.android.mvvmsample.ui.issues.viewmodel;

import java.util.List;

import rx.Observable;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueViewModel {
    void refreshIssues();

    void fetchIssues();

    boolean showIndicator();

    Observable<List<IssueItemViewModel>> observIssues();
}
