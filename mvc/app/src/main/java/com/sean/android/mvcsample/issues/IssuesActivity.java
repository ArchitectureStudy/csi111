package com.sean.android.mvcsample.issues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.util.ActivityUtils;
import com.sean.android.mvcsample.data.issue.IssuesRepository;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Sample Github
 * Github Id : JakeWharton
 * Github Repository : DiskLruCache
 */
public class IssuesActivity extends AppCompatActivity {
    private static final String TAG = IssuesActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    IssuesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        IssuesFragment issuesFragment = (IssuesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        if (issuesFragment == null) {
            issuesFragment = IssuesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issuesFragment, R.id.contentFrame);
        }

        mPresenter = new IssuesPresenter(IssuesRepository.getInstance(), issuesFragment);
    }
}
