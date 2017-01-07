package com.sean.android.mvcsample.issues;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.model.Model;
import com.sean.android.mvcsample.base.model.ModelStateListener;
import com.sean.android.mvcsample.base.view.ProgressIndicator;
import com.sean.android.mvcsample.issues.adapter.IssuesAdapter;
import com.sean.android.mvcsample.issues.controller.IssuesController;
import com.sean.android.mvcsample.issues.controller.IssueControllerStateListener;
import com.sean.android.mvcsample.issues.model.Issues;
import com.sean.android.mvcsample.issues.model.IssuesStateListener;

public class IssuesActivity extends AppCompatActivity implements IssuesStateListener, IssueControllerStateListener {

    private RecyclerView recyclerView;
    private IssuesAdapter issuesAdapter;

    private ProgressIndicator progressIndicator;
    private IssuesController issuesController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        progressIndicator = new ProgressIndicator(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_issues);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        issuesController = new IssuesController();
        issuesController.setIssueControllerStateListener(this);
        issuesController.setModelStateListener(this);
        issuesController.executeIssuesService();
    }


    @Override
    public void updateModelStarted() {
        progressIndicator.show();
    }

    @Override
    public void updateModelFinished() {
        progressIndicator.dismiss();
    }

    @Override
    public void updateModelFailed() {
        progressIndicator.dismiss();
    }

    @Override
    public void onModelStateUpdated(Issues model) {
        if(issuesAdapter == null) {
            issuesAdapter = new IssuesAdapter(model);
            recyclerView.setAdapter(issuesAdapter);
        }
        else {
            issuesAdapter.setIssues(model);
        }

    }
}
