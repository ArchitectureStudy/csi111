package com.sean.android.mvvmsample.ui.issues.viewmodel;

import com.sean.android.mvvmsample.base.command.MessageNotifyCommand;
import com.sean.android.mvvmsample.base.viewmodel.NotifyUpdateViewModelListener;

import java.util.List;

import rx.Observable;

/**
 * Created by Seonil on 2017-02-17.
 */

public interface IssueViewModel {
    void refreshIssues();

    void fetchIssues();

    void setNotifyCommand(MessageNotifyCommand messageNotifyCommand);

    void setUpdateViewModelListener(NotifyUpdateViewModelListener listener);
}
