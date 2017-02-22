package com.sean.android.mvvmsample.ui.issues;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.base.util.ActivityUtils;
import com.sean.android.mvvmsample.data.issue.IssuesRepository;
import com.sean.android.mvvmsample.databinding.ActivityIssuesBinding;

import butterknife.ButterKnife;


/**
 * Sample Github
 * Github Id : JakeWharton
 * Github Repository : DiskLruCache
 */
public class IssuesActivity extends AppCompatActivity {

    IssuesPresenter mPresenter;

    ActivityIssuesBinding activityIssuesBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityIssuesBinding = DataBindingUtil.setContentView(this, R.layout.activity_issues);
        setSupportActionBar(activityIssuesBinding.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (activityIssuesBinding.navView != null) {
            setupDrawerContent(activityIssuesBinding.navView);
        }

        IssuesFragment issuesFragment = (IssuesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);


        if (issuesFragment == null) {
            issuesFragment = IssuesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), issuesFragment, R.id.contentFrame);
        }

        mPresenter = new IssuesPresenter(IssuesRepository.getInstance(), issuesFragment);
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
                        activityIssuesBinding.drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
