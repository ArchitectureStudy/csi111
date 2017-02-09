package com.sean.android.mvvmsample.ui.issues;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.sean.android.mvvmsample.R;
import com.sean.android.mvvmsample.data.issue.Issue;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by sean on 2017. 1. 7..
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder> {

    private List<Issue> mIssues;
    private IssueItemListener mIssueItemListener;

    public IssuesAdapter(List<Issue> issues, IssueItemListener issueItemListener) {
        setList(issues);
        mIssueItemListener = issueItemListener;
    }

    @Override
    public IssuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_issue_item, null);
        IssuesViewHolder issuesViewHolder = new IssuesViewHolder(itemLayoutView);
        return issuesViewHolder;
    }

    @Override
    public void onBindViewHolder(IssuesViewHolder holder, final int position) {
        final Issue issue = mIssues.get(position);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIssueItemListener.onIssueClick(issue);
            }
        });
        holder.titleTextView.setText(mIssues.get(position).getTitle());
        holder.idTextView.setText("#" + mIssues.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    private void setList(List<Issue> issues) {
        this.mIssues = checkNotNull(issues);
    }

    public void replaceData(List<Issue> issues) {
        setList(issues);
        notifyDataSetChanged();
    }


    static class IssuesViewHolder extends RecyclerView.ViewHolder {
        public View rootView;

        @BindView(R.id.title_textView)
        public TextView titleTextView;

        @BindView(R.id.id_textView)
        public TextView idTextView;


        public IssuesViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IssueItemListener {
        void onIssueClick(Issue issue);
    }
}
