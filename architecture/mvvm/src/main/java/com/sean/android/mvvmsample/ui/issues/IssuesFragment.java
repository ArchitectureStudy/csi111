package com.sean.android.mvvmsample.ui.issues;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.base.command.ToastNotifyCommand;
import com.sean.android.mvvmsample.base.viewmodel.NotifyUpdateViewModelListener;
import com.sean.android.mvvmsample.databinding.FragmentIssuesBinding;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssueItemViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssueViewModel;
import com.sean.android.mvvmsample.ui.issues.viewmodel.IssueViewModelImpl;

import java.util.List;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment {

    private IssuesAdapter mIssuesAdapter;

    private FragmentIssuesBinding fragmentIssuesBinding;

    private IssueViewModel issuesViewModel;

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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIssuesAdapter = new IssuesAdapter();
        issuesViewModel = new IssueViewModelImpl();
        issuesViewModel.setNotifyCommand(new ToastNotifyCommand(getActivity()));
        issuesViewModel.setUpdateViewModelListener(new NotifyUpdateViewModelListener<List<IssueItemViewModel>>() {
            @Override
            public void onUpdatedViewModel(List<IssueItemViewModel> viewModel) {
                mIssuesAdapter.replaceData(viewModel);
                if (fragmentIssuesBinding.scrollChildSwipeRefreshLayout.isRefreshing()) {
                    fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
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
        fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                issuesViewModel.refreshIssues();
            }
        });
        issuesViewModel.fetchIssues();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
