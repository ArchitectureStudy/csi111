package com.sean.android.mvcsample.issuedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.util.ActivityUtils;
import com.sean.android.mvcsample.data.issue.IssuesRepository;
import com.sean.android.mvcsample.issues.IssuesFragment;
import com.sean.android.mvcsample.issues.IssuesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sean on 2017. 1. 18..
 */

public class IssueDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ISSUE_NUMBER = "ISSUE_NUMBER";


    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuedetail);
        ButterKnife.bind(this);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }

//        IssuesFragment issuesFragment = (IssuesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//
//
//        if (issuesFragment == null) {
//            issuesFragment = IssuesFragment.newInstance();
//            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issuesFragment, R.id.contentFrame);
//        }

//        mPresenter = new IssuesPresenter(IssuesRepository.getInstance(), issuesFragment);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.list_navigation_issues_item:
                                break;

                            default:
                                break;
                        }
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
