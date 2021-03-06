package com.sean.android.mvvmsample.ui.issuedetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.databinding.LayoutCommentItemBinding;
import com.sean.android.mvvmsample.ui.issuedetail.viewmodel.CommentItemViewModel;

import java.util.Collections;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by sean on 2017. 1. 7..
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {

    private List<CommentItemViewModel> mCommentViewModels;

    public CommentsAdapter() {
        setList(Collections.<CommentItemViewModel>emptyList());
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutCommentItemBinding commentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_comment_item, parent, false);

        return new CommentViewHolder(commentItemBinding);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, final int position) {
        holder.bind(mCommentViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentViewModels.size();
    }

    private void setList(List<CommentItemViewModel> comments) {
        this.mCommentViewModels = checkNotNull(comments);
    }

    public void replaceData(List<CommentItemViewModel> commentItemViewModels) {
        setList(commentItemViewModels);
        notifyDataSetChanged();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        LayoutCommentItemBinding layoutCommentItemBinding;

        public CommentViewHolder(LayoutCommentItemBinding commentItemBinding) {
            super(commentItemBinding.itemComment);
            this.layoutCommentItemBinding = commentItemBinding;
        }

        void bind(CommentItemViewModel viewModel) {
            layoutCommentItemBinding.setItemViewModel(viewModel);
        }
    }
}
