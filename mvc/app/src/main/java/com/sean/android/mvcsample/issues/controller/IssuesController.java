package com.sean.android.mvcsample.issues.controller;

import android.util.Log;

import com.sean.android.mvcsample.base.model.ModelStateListener;
import com.sean.android.mvcsample.base.network.HttpResponseData;
import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.issues.model.GetIssuesFromGithubServiceWorker;
import com.sean.android.mvcsample.issues.model.GithubUser;
import com.sean.android.mvcsample.issues.model.Issue;
import com.sean.android.mvcsample.issues.model.Issues;
import com.sean.android.mvcsample.issues.model.dto.IssueDTO;

import java.util.List;

/**
 * Created by sean on 2017. 1. 7..
 */

public class IssuesController implements ServiceWorker.ServiceWorkEventListener{
    private static final String TAG = IssuesController.class.getName();

    private GithubUser githubUser;
    private GetIssuesFromGithubServiceWorker getIssuesFromGithubServiceWorker;


    private Issues issues;

    private IssueControllerStateListener issueControllerStateListener;

    private ModelStateListener modelStateListener;

    public IssuesController() {

        issues = new Issues();
        githubUser = createDummyData();
        getIssuesFromGithubServiceWorker = new GetIssuesFromGithubServiceWorker(githubUser);
        getIssuesFromGithubServiceWorker.setServiceWorkEventListener(this);

    }

    private void executeIssuesService() {
        if(getIssuesFromGithubServiceWorker != null && githubUser.validateGitData()) {
            getIssuesFromGithubServiceWorker.executeAsync();
        }
    }

    private GithubUser createDummyData() {
        return new GithubUser("JakeWharton", "DiskLruCache");
    }

    @Override
    public void onPreExecute() {
        if(issueControllerStateListener != null) {
            issueControllerStateListener.updateModelStarted();
        }
    }

    @Override
    public void onComplete(HttpResponseData result) {
        if(result.getResponseData() instanceof List) {
            List<IssueDTO> issuesDTO = (List<IssueDTO>) result.getResponseData();

            for(IssueDTO issueDTO : issuesDTO){
                issues.add(Issue.convertModel(issueDTO));
            }
            issueControllerStateListener.updateModelFinished();
            modelStateListener.onModelStateUpdated(issues);
        }
    }

    @Override
    public void onFail(Throwable e) {
        Log.d(TAG, "onFail\n[" + e.getMessage() + "]");
        issueControllerStateListener.updateModelFailed();
    }

    public void setIssueControllerStateListener(IssueControllerStateListener issueControllerStateListener) {
        this.issueControllerStateListener = issueControllerStateListener;
    }

    public void setModelStateListener(ModelStateListener modelStateListener) {
        this.modelStateListener = modelStateListener;
    }

    public void eventActionInqueryIssues(String githubId, String githubRepo) {
        if(githubUser == null) {
            githubUser = new GithubUser(githubId, githubRepo);
        }
        else {
            githubUser.setUser(githubId);
            githubUser.setRepository(githubRepo);
        }

        executeIssuesService();
    }
}
