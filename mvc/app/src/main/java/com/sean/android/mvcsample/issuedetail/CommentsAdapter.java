package com.sean.android.mvcsample.issuedetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.data.comment.Comment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by sean on 2017. 1. 7..
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    private List<Comment> mComments;

    public CommentsAdapter(List<Comment> comments) {
        setList(comments);
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, null);
        CommentsViewHolder commentsViewHolder = new CommentsViewHolder(itemLayoutView);
        return commentsViewHolder;
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, final int position) {
        final Comment comment = mComments.get(position);

        holder.nameTextView.setText(comment.getUser().getLogin());
        holder.commentTextView.setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    private void setList(List<Comment> comments) {
        this.mComments = checkNotNull(comments);
    }

    public void replaceData(List<Comment> comments) {
        setList(comments);
        notifyDataSetChanged();
    }


    static class CommentsViewHolder extends RecyclerView.ViewHolder {
        public View rootView;

        @BindView(R.id.user_name_textView)
        public TextView nameTextView;

        @BindView(R.id.user_comment_textView)
        public TextView commentTextView;


        public CommentsViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
