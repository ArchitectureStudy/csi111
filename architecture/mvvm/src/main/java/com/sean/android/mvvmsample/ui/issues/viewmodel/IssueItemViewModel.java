package com.sean.android.mvvmsample.ui.issues.viewmodel;

import android.view.View;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueItemViewModel {

    String getTitleText();

    String getIssueIdText();

    void onItemClick(View view);
}
