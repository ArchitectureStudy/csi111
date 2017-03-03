package com.sean.android.vipersample.ui.issues.viewmodel;


import com.sean.android.vipersample.base.command.MessageNotifyCommand;
import com.sean.android.vipersample.base.viewmodel.NotifyUpdateViewModelListener;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueViewModel {
    void refreshIssues();

    void fetchIssues();

    void setNotifyCommand(MessageNotifyCommand messageNotifyCommand);

    void setUpdateViewModelListener(NotifyUpdateViewModelListener listener);
}
