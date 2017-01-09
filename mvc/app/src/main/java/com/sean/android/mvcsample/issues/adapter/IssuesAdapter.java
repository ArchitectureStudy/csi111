package com.sean.android.mvcsample.issues.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.issues.model.Issues;

/**
 * Created by sean on 2017. 1. 7..
 */

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder> {

    private Issues issues;

    public IssuesAdapter(Issues issues) {
        this.issues = issues;
    }

    @Override
    public IssuesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_issue_item, null);
        IssuesViewHolder issuesViewHolder = new IssuesViewHolder(itemLayoutView);
        return issuesViewHolder;
    }

    @Override
    public void onBindViewHolder(IssuesViewHolder holder, int position) {
        holder.titleTextView.setText(issues.get(position).getTitle());
        holder.idTextView.setText("#" + issues.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return issues.count();
    }

    public void setIssues(Issues issues) {
        this.issues = issues;
        notifyDataSetChanged();
    }

    static class IssuesViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView idTextView;


        public IssuesViewHolder(View itemView) {
            super(itemView);

            idTextView = (TextView) itemView.findViewById(R.id.id_textView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_textView);
        }
    }
}
