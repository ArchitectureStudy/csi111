package com.sean.android.mvvmsample.ui.issues;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.databinding.FragmentIssuesBinding;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssueItemViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssuesViewModelImpl;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment {

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
        mIssuesAdapter = new IssuesAdapter();
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
}
