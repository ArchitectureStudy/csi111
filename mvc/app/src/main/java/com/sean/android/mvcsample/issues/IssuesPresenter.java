package com.sean.android.mvcsample.issues;

import android.support.annotation.NonNull;

import com.sean.android.mvcsample.data.issue.Issue;
import com.sean.android.mvcsample.data.issue.Issues;
import com.sean.android.mvcsample.data.issue.IssuesDataSource;
import com.sean.android.mvcsample.data.issue.IssuesRepository;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesPresenter implements IssuesContract.Presenter {

    private final IssuesRepository mIssuesRepository;

    private IssuesContract.View mIssuesView;

    private boolean mFirstLoad = true;


    public IssuesPresenter(@NonNull IssuesRepository issuesRepository, @NonNull IssuesContract.View issuesView) {
        mIssuesRepository = checkNotNull(issuesRepository);
        mIssuesView = checkNotNull(issuesView);
        mIssuesView.setPresenter(this);
    }

    @Override
    public void loadIssues(boolean forceUpdate) {
        loadIssues(forceUpdate || mFirstLoad, true);
        mFirstLoad = false;
    }

    @Override
    public void clearIssues() {

    }

    @Override
    public void openIssueDetail(Issue issue) {
        mIssuesView.showIssueDetailUI(issue.getNumber(), issue.getTitle(), issue.getBody());
    }

    @Override
    public void start() {
        loadIssues(false);
    }

    private void loadIssues(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mIssuesView.setLoadingIndicator(true);
        }

        if (forceUpdate) {
            mIssuesRepository.refreshIssues();
        }


        mIssuesRepository.getIssues(new IssuesDataSource.LoadIssuesCallback() {
            @Override
            public void onIssuesLoaded(Issues issues) {


                if (showLoadingUI) {
                    mIssuesView.setLoadingIndicator(false);
                }

                mIssuesView.showIssues(issues.getModels());
            }

            @Override
            public void onIssuesFailed(int code, String message) {
                if (showLoadingUI) {
                    mIssuesView.setLoadingIndicator(false);
                }

                mIssuesView.showLoadingIssuesError();
            }
        });
    }
}
