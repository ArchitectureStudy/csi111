package com.sean.android.mvvmsample.ui.issuedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.databinding.FragmentIssueDetailBinding;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.CommentCommander;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.CommentsViewModel;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.CommentsViewModelImpl;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.IssueDetailViewModel;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Seonil on 2017-01-20.
 */
public class IssueDetailFragment extends Fragment {
    private CommentsAdapter mCommentsAdapter;

    private FragmentIssueDetailBinding fragmentIssueDetailBinding;

    private IssueDetailViewModel issueDetailViewModel;

    private CommentsViewModel commentsViewModel;

    private CommentCommander commander;

    private Subscription subscription;

    public static IssueDetailFragment newInstance() {
        return new IssueDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommentsAdapter = new CommentsAdapter();
        issueDetailViewModel = getArguments().getParcelable(IssueDetailViewModel.class.getName());
        if (issueDetailViewModel != null) {
            commentsViewModel = new CommentsViewModelImpl(getActivity(), issueDetailViewModel.getIssueNumber());
            subscribe();
            commentsViewModel.fetchComments();
            issueDetailViewModel.setCommander((CommentCommander) commentsViewModel);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentIssueDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_detail, container, false);
        fragmentIssueDetailBinding.commentsRecyclerView.setLayoutManager(new CommentsRecyclerViewLayoutManager(getContext()));
        fragmentIssueDetailBinding.commentsRecyclerView.setAdapter(mCommentsAdapter);
        return fragmentIssueDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (issueDetailViewModel != null) {
            fragmentIssueDetailBinding.setIssueDetailViewModel(issueDetailViewModel);
            ((AppCompatActivity) getActivity()).setSupportActionBar(fragmentIssueDetailBinding.issueDetailToolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void subscribe() {
        if (commentsViewModel != null) {
            subscription = commentsViewModel.observComments().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<CommentItemViewModel>>() {
                @Override
                public void call(List<CommentItemViewModel> commentItemViewModels) {
                    mCommentsAdapter.replaceData(commentItemViewModels);
                }
            });
        }
    }
}
