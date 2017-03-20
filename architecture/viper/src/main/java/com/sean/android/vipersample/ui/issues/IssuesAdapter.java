package com.sean.android.vipersample.ui.issues;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.android.vipersample.databinding.LayoutIssueItemBinding;
import com.sean.android.vipersample.ui.issues.presenter.IssuesPresenter;
import com.sean.android.vipersample.ui.issues.viewmodel.IssueItemViewModel;

import java.util.Collections;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by sean on 2017. 1. 7..
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder> {

    private IssuesPresenter mIssuePresenter;

    private List<IssueItemViewModel> mIssueViewModels;

    public IssuesAdapter(IssuesPresenter issuePresenter) {
        this.mIssuePresenter = issuePresenter;
        setList(Collections.<IssueItemViewModel>emptyList());
    }

    @Override
    public IssuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IssuesViewHolder(LayoutIssueItemBinding.inflate(LayoutInflater.from(parent.getContext())), mIssuePresenter);
    }

    @Override
    public void onBindViewHolder(IssuesViewHolder holder, final int position) {
        holder.bind(mIssueViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mIssueViewModels.size();
    }

    private void setList(List<IssueItemViewModel> issues) {
        this.mIssueViewModels = checkNotNull(issues);
    }

    public void replaceData(List<IssueItemViewModel> issues) {
        setList(issues);
        notifyDataSetChanged();
    }


    static class IssuesViewHolder extends RecyclerView.ViewHolder {
        LayoutIssueItemBinding layoutIssueItemBinding;

        public IssuesViewHolder(final LayoutIssueItemBinding layoutIssueItemBinding, final IssuesPresenter issuePresenter) {
            super(layoutIssueItemBinding.getRoot());
            this.layoutIssueItemBinding = layoutIssueItemBinding;
            this.layoutIssueItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    issuePresenter.onItemClicked(layoutIssueItemBinding.getItemViewModel());
                }
            });
        }

        void bind(IssueItemViewModel viewModel) {
            layoutIssueItemBinding.setItemViewModel(viewModel);
        }
    }
}
