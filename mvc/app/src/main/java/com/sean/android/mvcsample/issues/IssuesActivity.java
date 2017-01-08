package com.sean.android.mvcsample.issues;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.view.ProgressIndicator;
import com.sean.android.mvcsample.issues.adapter.IssuesAdapter;
import com.sean.android.mvcsample.issues.adapter.IssuesRecyclerViewLayoutManager;
import com.sean.android.mvcsample.issues.controller.IssueControllerStateListener;
import com.sean.android.mvcsample.issues.controller.IssuesController;
import com.sean.android.mvcsample.issues.model.Issues;
import com.sean.android.mvcsample.issues.model.IssuesStateListener;


/**
 * Sample Github
 Github Id : JakeWharton
 Github Repository : DiskLruCache
 */
public class IssuesActivity extends AppCompatActivity implements IssuesStateListener, IssueControllerStateListener {
    private static final String TAG = IssuesActivity.class.getName();

    private RecyclerView issuesRecyclerView;
    private IssuesAdapter issuesAdapter;

    private ProgressIndicator progressIndicator;
    private IssuesController issuesController;

    private EditText idInputEditText;
    private EditText repoInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        progressIndicator = new ProgressIndicator(this);

        issuesRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_issues);
        issuesRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(this));

        idInputEditText = (EditText) findViewById(R.id.editText_github_id);
        idInputEditText.setText("JakeWharton");
        repoInputEditText = (EditText) findViewById(R.id.editText_github_repo);
        repoInputEditText.setText("DiskLruCache");

        issuesController = new IssuesController();
        issuesController.setIssueControllerStateListener(this);
        issuesController.setModelStateListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.issues, menu);
        return true;
    }

    @Override
    public void updateModelStarted() {
        Log.d(TAG, "updateModelStarted");
        progressIndicator.show();
    }

    @Override
    public void updateModelFinished() {
        Log.d(TAG, "updateModelFinished");
        progressIndicator.dismiss();
    }

    @Override
    public void updateModelFailed() {
        Log.d(TAG, "updateModelFailed");
        progressIndicator.dismiss();
    }

    @Override
    public void onModelStateUpdated(Issues model) {
        if(issuesAdapter == null) {
            issuesAdapter = new IssuesAdapter(model);
            issuesRecyclerView.setAdapter(issuesAdapter);
        }
        else {
            issuesAdapter.setIssues(model);
        }

    }

    public void executeIssuesInquery(MenuItem v){
        issuesController.eventActionInqueryIssues(idInputEditText.getText().toString(), repoInputEditText.getText().toString());
    }
}
