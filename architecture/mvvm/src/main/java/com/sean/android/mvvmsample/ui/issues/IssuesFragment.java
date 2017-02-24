package com.sean.android.mvvmsample.ui.issues;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.data.issue.Issue;
import com.sean.android.mvvmsample.databinding.FragmentIssuesBinding;
import com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssueItemViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesViewModelImpl;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_BODY;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_NUMBER;
import static com.sean.android.mvvmsample.ui.issuedetail.IssueDetailActivity.EXTRA_ISSUE_TITLE;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment implements IssuesAdapter.IssueItemListener {

    private IssuesAdapter mIssuesAdapter;

    private FragmentIssuesBinding fragmentIssuesBinding;

    private IssuesViewModel issuesViewModel;

    private Subscription subscription;

    public static IssuesFragment newInstance() {
        return new IssuesFragment();
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        issuesViewModel = new IssuesViewModelImpl(activity);
        subscribe();
    }

    private void subscribe() {
        if (issuesViewModel != null) {
            subscription = issuesViewModel.observIssues().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<IssueItemViewModel>>() {
                @Override
                public void call(List<IssueItemViewModel> issueItemViewModels) {
                    mIssuesAdapter.replaceData(issueItemViewModels);
                }
            });
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssuesAdapter = new IssuesAdapter(new ArrayList(0), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        fragmentIssuesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false);
        return fragmentIssuesBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentIssuesBinding.issuesRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(getContext()));
        fragmentIssuesBinding.issuesRecyclerView.setAdapter(mIssuesAdapter);
        issuesViewModel.fetchIssues();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
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
