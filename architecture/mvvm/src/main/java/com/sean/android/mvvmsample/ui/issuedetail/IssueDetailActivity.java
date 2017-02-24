package com.sean.android.mvvmsample.ui.issuedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.base.util.ActivityUtils;
import com.sean.android.mvvmsample.data.issue.Issue;

/**
 * Created by sean on 2017. 1. 18..
 */

public class IssueDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ISSUE_NUMBER = "ISSUE_NUMBER";
    public static final String EXTRA_ISSUE_TITLE = "ISSUE_TITLE";
    public static final String EXTRA_ISSUE_BODY = "ISSUE_BODY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuedetail);

        IssueDetailFragment issueDetailFragment = (IssueDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (issueDetailFragment == null) {
            issueDetailFragment = IssueDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issueDetailFragment, R.id.contentFrame);
        }
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
            Issue issue = new Issue();
            issue.setNumber(getIntent().getIntExtra(EXTRA_ISSUE_NUMBER, -1));
            issue.setTitle(getIntent().getStringExtra(EXTRA_ISSUE_TITLE));
            issue.setBody(getIntent().getStringExtra(EXTRA_ISSUE_BODY));
        }

    }


}
