package com.sean.android.vipersample.ui.issues;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sean.android.vipersample.R;
import com.sean.android.vipersample.base.util.ActivityUtils;
import com.sean.android.vipersample.databinding.ActivityIssuesBinding;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenter;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenterBinder;
import com.sean.android.vipersample.ui.issues.router.IssueRouter;


/**
 * Sample Github
 * Github Id : JakeWharton
 * Github Repository : DiskLruCache
 */
public class IssuesActivity extends AppCompatActivity {

    private ActivityIssuesBinding activityIssuesBinding;

    private IssuesModule mIssuesModule;
    private IssuesPresenter mIssuePresenter;
    private IssueRouter mIssueRouter;

    private IssuesPresenterBinder mIssuesPresenterBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityIssuesBinding = DataBindingUtil.setContentView(this, R.layout.activity_issues);
        setSupportActionBar(activityIssuesBinding.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mIssuesModule = new IssuesModule(this);

        mIssueRouter = mIssuesModule.getRouter();

        mIssuePresenter = IssuesModule.createIssuePresenter(IssuesModule.createIssueInteractor());

        IssuesFragment issuesFragment = (IssuesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (issuesFragment == null) {
            issuesFragment = IssuesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issuesFragment, R.id.contentFrame);
        }
        mIssuesPresenterBinder = issuesFragment;

        mIssuePresenter.attachView(issuesFragment);
        mIssuePresenter.attachRouter(mIssueRouter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIssuesPresenterBinder.bindPresenter(mIssuePresenter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mIssuesPresenterBinder.unbindPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                activityIssuesBinding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.list_navigation_issues_item:
                    break;

                default:
                    break;
            }
            menuItem.setChecked(true);
            activityIssuesBinding.drawerLayout.closeDrawers();
            return true;
        }
    };
}
