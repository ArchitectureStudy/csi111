package com.sean.android.vipersample.ui.issuedetail.viewmodel;

import android.os.Parcelable;
import android.view.View;

import com.sean.android.vipersample.base.command.MessageNotifyCommand;
import com.sean.android.vipersample.base.databinding.BindableString;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueDetailViewModel extends Parcelable {

    String getTitleText();

    String getContentsText();

    BindableString getCommentText();

    int getIssueNumber();

    void onClickSendComment(View view);

    void setCommander(CommentCommander commander);

    void setNotifyCommand(MessageNotifyCommand messageNotifyCommand);
}
