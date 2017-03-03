package com.sean.android.vipersample.ui.issues.viewmodel;

import android.content.Intent;
import android.view.View;

import com.sean.android.vipersample.data.issue.Issue;
import com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity;

import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_BODY;
import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;
import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_TITLE;


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
    public void onItemClick(View view) {
        Intent intent = new Intent(view.getContext(), IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_NUMBER, mIssue.getNumber());
        intent.putExtra(EXTRA_ISSUE_TITLE, mIssue.getTitle());
        intent.putExtra(EXTRA_ISSUE_BODY, mIssue.getBody());
        view.getContext().startActivity(intent);
    }
}
