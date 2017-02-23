package com.sean.android.mvvmsample.ui.issues;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.data.issue.Issue;
import com.sean.android.mvvmsample.databinding.FragmentIssuesBinding;
import com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesVIewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesViewModelImpl;

import java.util.ArrayList;

import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_BODY;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_TITLE;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment implements IssuesAdapter.IssueItemListener {

    private IssuesAdapter mIssuesAdapter;

    private FragmentIssuesBinding fragmentIssuesBinding;

    private IssuesVIewModel issuesVIewModel;

    public static IssuesFragment newInstance() {
        return new IssuesFragment();
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        issuesVIewModel = new IssuesViewModelImpl(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        issuesVIewModel = new IssuesViewModelImpl(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssuesAdapter = new IssuesAdapter(new ArrayList<Issue>(0), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false);
        return fragmentIssuesBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentIssuesBinding.issuesRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(getContext()));

        if (mIssuesAdapter != null && mIssuesAdapter.getItemCount() > 0) {
            fragmentIssuesBinding.issuesRecyclerView.setAdapter(mIssuesAdapter);
        } else {
            issuesVIewModel.fetchIssues();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void showIssueDetailUI(int issueNumber, String title, String body) {
        Intent intent = new Intent(getContext(), IssueDetailActivity.class);
        intent.putExtra(EXTRA_ISSUE_NUMBER, issueNumber);
        intent.putExtra(EXTRA_ISSUE_TITLE, title);
        intent.putExtra(EXTRA_ISSUE_BODY, body);
        startActivity(intent);
    }

    @Override
    public void onIssueClick(Issue issue) {

    }
}
