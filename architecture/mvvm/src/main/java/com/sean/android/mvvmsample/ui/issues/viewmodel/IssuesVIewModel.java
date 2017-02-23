package com.sean.android.mvvmsample.ui.issues.viewmodel;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssuesVIewModel {
    void refreshIssues();

    void fetchIssues();

    boolean showIndicator();
}
