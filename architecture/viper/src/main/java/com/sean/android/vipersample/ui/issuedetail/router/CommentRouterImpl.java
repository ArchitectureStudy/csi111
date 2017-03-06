package com.sean.android.vipersample.ui.issuedetail.router;

import com.sean.android.vipersample.ui.issuedetail.IssueDetailActivity;

/**
 * Created by Seonil on 2017-03-06.
 */

public class CommentRouterImpl implements CommentRouter {
    private IssueDetailActivity mIssueDetailActivity;

    public CommentRouterImpl(IssueDetailActivity issueDetailActivity) {
        this.mIssueDetailActivity = issueDetailActivity;
    }
}
