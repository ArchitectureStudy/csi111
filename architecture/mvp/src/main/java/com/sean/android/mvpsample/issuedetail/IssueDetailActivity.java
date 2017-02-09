package com.sean.android.mvpsample.issuedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sean.android.mvpsample.R;
import com.sean.android.mvpsample.base.util.ActivityUtils;
import com.sean.android.mvpsample.data.comment.CommentsRepository;
import com.sean.android.mvpsample.data.issue.IssuesRepository;

/**
 * Created by sean on 2017. 1. 18..
 */

public class IssueDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ISSUE_NUMBER = "ISSUE_NUMBER";
    public static final String EXTRA_ISSUE_TITLE = "ISSUE_TITLE";
    public static final String EXTRA_ISSUE_BODY = "ISSUE_BODY";


    IssueDetailPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuedetail);

        int issueNumber = getIntent().getIntExtra(EXTRA_ISSUE_NUMBER, -1);
        String issueTitle = getIntent().getStringExtra(EXTRA_ISSUE_TITLE);
        String issueBody = getIntent().getStringExtra(EXTRA_ISSUE_BODY);

        IssueDetailFragment issueDetailFragment = (IssueDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (issueDetailFragment == null) {
            issueDetailFragment = IssueDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issueDetailFragment, R.id.contentFrame);
        }

        mPresenter = new IssueDetailPresenter(issueNumber, issueTitle, issueBody, CommentsRepository.getInstance(), IssuesRepository.getInstance(), issueDetailFragment);
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


}
