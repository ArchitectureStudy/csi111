package com.sean.android.mvvmsample.ui.issues.viewmodel;

import android.content.Intent;
import android.view.View;

import com.sean.android.mvvmsample.data.issue.Issue;
import com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity;

import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_BODY;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_TITLE;

/**
 * Created by Seonil on 2017-02-23.
 */

public class IssueItemViewModelImpl implements IssueItemViewModel {
    Issue issue;

    public IssueItemViewModelImpl(Issue issue) {
        this.issue = issue;
    }

    @Override
    public String getTitleText() {
        return issue.getTitle();
    }

    @Override
    public String getIssueIdText() {
        return String.valueOf(issue.getId());
    }

    @Override
    public void onItemClick(View view) {
        Intent intent = new Intent(view.getContext(), IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_NUMBER, issue.getNumber());
        intent.putExtra(EXTRA_ISSUE_TITLE, issue.getTitle());
        intent.putExtra(EXTRA_ISSUE_BODY, issue.getBody());
        view.getContext().startActivity(intent);
    }
}
