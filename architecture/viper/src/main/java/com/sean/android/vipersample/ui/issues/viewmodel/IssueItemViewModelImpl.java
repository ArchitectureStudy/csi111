package com.sean.android.vipersample.ui.issues.viewmodel;

import com.sean.android.vipersample.data.issue.Issue;


/**
 * Created by Seonil on 2017-02-23.
 */

public class IssueItemViewModelImpl implements IssueItemViewModel {

    private Issue mIssue;

    public IssueItemViewModelImpl(Issue issue) {
        this.mIssue = issue;
    }

    @Override
    public String getTitleText() {
        return mIssue.getTitle();
    }

    @Override
    public String getIssueIdText() {
        return String.valueOf(mIssue.getId());
    }

    @Override
    public int getIssueNumber() {
        return mIssue.getNumber();
    }

    @Override
    public String getIssueContentsText() {
        return mIssue.getBody();
    }
}
