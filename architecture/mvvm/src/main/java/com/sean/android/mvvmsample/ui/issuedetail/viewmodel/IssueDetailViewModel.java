package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import android.view.View;

import com.sean.android.mvvmsample.base.databinding.BindableString;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueDetailViewModel {

    String getTitleText();

    String getContentsText();

    BindableString getCommentText();

    void onClickSendComment(View view);
}
