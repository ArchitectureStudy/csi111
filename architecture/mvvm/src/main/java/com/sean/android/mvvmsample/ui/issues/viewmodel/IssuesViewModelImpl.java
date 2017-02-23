package com.sean.android.mvvmsample.ui.issues.viewmodel;

import android.content.Context;

import com.sean.android.mvvmsample.base.util.ToastMaker;
import com.sean.android.mvvmsample.data.issue.Issue;
import com.sean.android.mvvmsample.data.issue.Issues;
import com.sean.android.mvvmsample.data.issue.IssuesDataSource;
import com.sean.android.mvvmsample.data.issue.IssuesRepository;

import java.util.List;

/**
 * Created by Seonil on 2017-02-23.
 */

public class IssuesViewModelImpl implements IssuesVIewModel {

    private Context mContext;

    private List<Issue> mIssueList;

    public IssuesViewModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void refreshIssues() {

    }

    @Override
    public void fetchIssues() {
        IssuesRepository.getInstance().fetchIssues(new IssuesDataSource.LoadIssuesCallback() {
            @Override
            public void onIssuesLoaded(Issues issues) {
                mIssueList = issues.getModels();
            }

            @Override
            public void onIssuesFailed(int code, String message) {
                ToastMaker.makeShortToast(mContext, message);
            }
        });

    }

    @Override
    public boolean showIndicator() {
        return false;
    }
}
