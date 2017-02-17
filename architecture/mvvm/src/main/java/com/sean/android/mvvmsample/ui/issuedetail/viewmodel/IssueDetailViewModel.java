package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import android.view.View;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueDetailViewModel {

    String getTitleText();

    String getContentsText();

    void onClickSendComment(View view);
}
