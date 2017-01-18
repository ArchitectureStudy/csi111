package com.sean.android.mvcsample.issues;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.data.issue.Issue;
import com.sean.android.mvcsample.issuedetail.IssueDetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.sean.android.mvcsample.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment implements IssuesContract.View, IssuesAdapter.IssueItemListener {

    private IssuesContract.Presenter mPresenter;

    private IssuesAdapter mIssuesAdapter;

    private RecyclerView mRecyclerView;

    public static IssuesFragment newInstance() {
        return new IssuesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssuesAdapter = new IssuesAdapter(new ArrayList<Issue>(0), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_issues, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.issues_recyclerView);
        mRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(getContext()));
        mRecyclerView.setAdapter(mIssuesAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setLoadingIndicator(boolean active) {
    }

    @Override
    public void showIssues(List<Issue> issueList) {
        mIssuesAdapter.replaceData(issueList);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showLoadingIssuesError() {
    }

    @Override
    public void showIssueDetailUI(int issueNumber) {
        Intent intent = new Intent(getContext(), IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_NUMBER, issueNumber);
        startActivity(intent);
    }

    @Override
    public void setPresenter(@NonNull IssuesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onIssueClick(Issue issue) {
        mPresenter.openIssueDetail(issue);
    }
}
