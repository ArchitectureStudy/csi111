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
import com.sean.android.vipersample.data.issue.Issue;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModelImpl;

/**
 * Created by sean on 2017. 1. 18..
 */

public class IssueDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ISSUE_NUMBER = "ISSUE_NUMBER";
    public static final String EXTRA_ISSUE_TITLE = "ISSUE_TITLE";
    public static final String EXTRA_ISSUE_BODY = "ISSUE_BODY";

    private IssueDetailViewModel issueDetailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuedetail);

        getIssueDataFromIntent(getIntent());

        IssueDetailFragment issueDetailFragment = (IssueDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (issueDetailFragment == null) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), createIssueDetailFragment(IssueDetailFragment.class), R.id.contentFrame);
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

            issueDetailViewModel = new IssueDetailViewModelImpl(issue);
        }
    }

    private Fragment createIssueDetailFragment(Class<?> fragmentClass) {
        BundleBuilder bundleBuilder = new BundleBuilder();
        bundleBuilder.with(IssueDetailViewModel.class.getName(), issueDetailViewModel);
        return Fragment.instantiate(this, fragmentClass.getName(), bundleBuilder.build());
    }
}
