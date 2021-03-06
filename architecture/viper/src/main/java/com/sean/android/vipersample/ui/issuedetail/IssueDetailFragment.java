package com.sean.android.vipersample.ui.issuedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.vipersample.R;
import com.sean.android.vipersample.base.util.ToastMaker;
import com.sean.android.vipersample.databinding.FragmentIssueDetailBinding;
import com.sean.android.vipersample.ui.issuedetail.presenter.CommentsPresenterBinder;
import com.sean.android.vipersample.ui.issuedetail.presenter.IssueDetailPresenter;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.IssueDetailViewModel;
import com.sean.android.vipersample.ui.issues.IssuesRecyclerViewLayoutManager;

import java.util.Collection;
import java.util.List;

/**
 * Created by Seonil on 2017-01-20.
 */
public class IssueDetailFragment extends Fragment implements IssueDetailViewCallbacks, CommentsPresenterBinder {
    private CommentsAdapter mCommentsAdapter;

    private FragmentIssueDetailBinding fragmentIssueDetailBinding;

    private IssueDetailPresenter issueDetailPresenter;

    public static IssueDetailFragment newInstance() {
        return new IssueDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentIssueDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_issue_detail, container, false);
        fragmentIssueDetailBinding.commentsRecyclerView.setLayoutManager(new CommentsRecyclerViewLayoutManager(getContext()));
        return fragmentIssueDetailBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentIssueDetailBinding.commentsRecyclerView.setLayoutManager(new IssuesRecyclerViewLayoutManager(getContext()));
        fragmentIssueDetailBinding.commentsRecyclerView.setAdapter(mCommentsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {
        ToastMaker.makeShortToast(getActivity(), message);
    }

    @Override
    public void onNewComments(Collection<CommentItemViewModel> itemViewModelCollection) {
        mCommentsAdapter.replaceData((List<CommentItemViewModel>) itemViewModelCollection);
    }

    @Override
    public void refreshComments() {
        issueDetailPresenter.fetchComments();
    }

    @Override
    public void onNewIssueDetail(IssueDetailViewModel issueDetailViewModel) {
        fragmentIssueDetailBinding.setIssueDetailViewModel(issueDetailViewModel);
    }

    @Override
    public void bindPresenter(final IssueDetailPresenter presenter) {
        this.issueDetailPresenter = presenter;
        mCommentsAdapter = new CommentsAdapter(presenter);
        fragmentIssueDetailBinding.setIssueDetailViewModel(presenter.getIssueDetailViewModel());
        fragmentIssueDetailBinding.commentsRecyclerView.setAdapter(mCommentsAdapter);
        fragmentIssueDetailBinding.sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickedComment(fragmentIssueDetailBinding.writeCommentEditText.getText().toString());
            }
        });
    }

    @Override
    public void unbindPresenter() {

    }
}
