package com.sean.android.vipersample.ui.issuedetail.viewmodel;

import android.os.Parcelable;

import com.sean.android.vipersample.base.databinding.BindableString;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueDetailViewModel extends Parcelable {

    BindableString getTitleText();

    BindableString getContentsText();

    BindableString getCommentText();
}
