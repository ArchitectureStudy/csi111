package com.sean.android.vipersample.ui.issuedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sean.android.vipersample.R;
import com.sean.android.vipersample.base.util.ActivityUtils;
import com.sean.android.vipersample.base.util.BundleBuilder;
import com.sean.android.vipersample.ui.issuedetail.interactor.CommentsInteractor;
import com.sean.android.vipersample.ui.issuedetail.presenter.CommentsPresenterBinder;
import com.sean.android.vipersample.ui.issuedetail.presenter.IssueDetailPresenter;
import com.sean.android.vipersample.ui.issuedetail.router.CommentRouter;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;
import com.sean.android.vipersample.ui.issues.IssuesFragment;

/**
 * Created by sean on 2017. 1. 18..
 */

public class IssueDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ISSUE_NUMBER = "ISSUE_NUMBER";
    public static final String EXTRA_ISSUE_TITLE = "ISSUE_TITLE";
    public static final String EXTRA_ISSUE_BODY = "ISSUE_BODY";

    private IssueDetailModule mIssueDetailModule;

    private IssueDetailPresenter mIssueDetailPresenter;

    private CommentsPresenterBinder mCommentsPresenterBinder;

    private CommentsInteractor mCommentsInteractor;

    private CommentRouter mCommentRouter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuedetail);

        mIssueDetailModule = new IssueDetailModule(this);

        mCommentRouter = mIssueDetailModule.getRouter();

        getIssueDataFromIntent(getIntent());
        mIssueDetailPresenter = IssueDetailModule.createIssuePresenter(mCommentsInteractor);

        IssueDetailFragment issueDetailFragment = (IssueDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (issueDetailFragment == null) {
            issueDetailFragment = IssueDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issueDetailFragment, R.id.contentFrame);
        }

        mCommentsPresenterBinder = issueDetailFragment;




        mIssueDetailPresenter.attachRouter(mCommentRouter);
        mIssueDetailPresenter.attachView(issueDetailFragment);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCommentsPresenterBinder.bindPresenter(mIssueDetailPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCommentsPresenterBinder.unbindPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getIssueDataFromIntent(Intent intent) {
        if (intent != null) {
            int number = getIntent().getIntExtra(EXTRA_ISSUE_NUMBER, -1);
            String title = getIntent().getStringExtra(EXTRA_ISSUE_TITLE);
            String body = getIntent().getStringExtra(EXTRA_ISSUE_BODY);
            mCommentsInteractor = IssueDetailModule.createIssueInteractor(number);
            mCommentsInteractor.setIssueBody(body);
            mCommentsInteractor.setIssueTitle(title);
        }
    }
}
