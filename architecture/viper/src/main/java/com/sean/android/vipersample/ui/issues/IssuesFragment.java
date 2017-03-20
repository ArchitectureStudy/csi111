package com.sean.android.vipersample.ui.issues;

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

import com.sean.android.vipersample.R;
import com.sean.android.vipersample.base.util.ToastMaker;
import com.sean.android.vipersample.databinding.FragmentIssuesBinding;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenter;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenterBinder;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;

import java.util.Collection;
import java.util.List;

/**
 * Created by Seonil on 2017-01-16.
 */

public class IssuesFragment extends Fragment implements IssuesViewCallbacks, IssuesPresenterBinder {

    private IssuesAdapter mIssuesAdapter;

    private FragmentIssuesBinding fragmentIssuesBinding;

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
    }

    @Override
    public void showProgress() {
        if (!fragmentIssuesBinding.scrollChildSwipeRefreshLayout.isRefreshing()) {
            fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void hideProgress() {
        if (fragmentIssuesBinding.scrollChildSwipeRefreshLayout.isRefreshing()) {
            fragmentIssuesBinding.scrollChildSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(String message) {
        ToastMaker.makeShortToast(getActivity(), message);
    }

    @Override
    public void onNewIssues(Collection<IssueItemViewModel> itemViewModelCollection) {
        mIssuesAdapter.replaceData((List<IssueItemViewModel>) itemViewModelCollection);
    }

    @Override
    public void bindPresenter(IssuesPresenter presenter) {
        if (mIssuesAdapter == null) {
            mIssuesAdapter = new IssuesAdapter(presenter);
        }
        
        fragmentIssuesBinding.issuesRecyclerView.setAdapter(mIssuesAdapter);
    }

    @Override
    public void unbindPresenter() {

    }
}
