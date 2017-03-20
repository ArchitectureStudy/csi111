package com.sean.android.vipersample.ui.issues.router;

import android.content.Intent;

import com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity;
import com.sean.android.vipersample.ui.issues.IssuesActivity;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;

import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_BODY;
import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;
import static com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_TITLE;

/**
 * Created by Seonil on 2017-03-06.
 */

public class IssueRouterImpl implements IssueRouter {
    private final IssuesActivity mIssuesActivity;

    public IssueRouterImpl(IssuesActivity mIssuesActivity) {
        this.mIssuesActivity = mIssuesActivity;
    }

    @Override
    public void navigateIssueDetail(IssueItemViewModel viewModel) {
        Intent intent = new Intent(mIssuesActivity, IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_NUMBER, viewModel.getIssueNumber());
        intent.putExtra(EXTRA_ISSUE_TITLE, viewModel.getTitleText());
        intent.putExtra(EXTRA_ISSUE_BODY, viewModel.getIssueContentsText());
        mIssuesActivity.startActivity(intent);
    }
}
