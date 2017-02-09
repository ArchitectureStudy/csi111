package com.sean.android.mvpsample.issues;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sean.android.mvpsample.R;
import com.sean.android.mvpsample.base.util.ActivityUtils;
import com.sean.android.mvpsample.data.issue.IssuesRepository;

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

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    IssuesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
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
                mDrawerLayout.openDrawer(GravityCompat.START);
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
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
}
