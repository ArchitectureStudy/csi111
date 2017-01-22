package com.sean.android.mvcsample.issuedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sean.android.mvcsample.R;
import com.sean.android.mvcsample.base.util.StringUtil;
import com.sean.android.mvcsample.data.comment.Comment;
import com.sean.android.mvcsample.issues.IssuesRecyclerViewLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Seonil on 2017-01-20.
 */

public class IssueDetailFragment extends Fragment implements IssueDetailContract.View {


    private IssueDetailContract.Presenter mPresenter;

    private CommentsAdapter mCommentsAdapter;

    @BindView(R.id.comments_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.issue_detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.issue_detail_body_textView)
    TextView mIssueBodyTextView;

    @BindView(R.id.write_comment_editText)
    EditText mWriteCommentEditText;


    @BindView(R.id.send_comment_button)
    Button mSendCommentButton;


    public static IssueDetailFragment newInstance() {
        return new IssueDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommentsAdapter = new CommentsAdapter(new ArrayList<Comment>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_issue_detail, container, false);
        ButterKnife.bind(this, root);
        mRecyclerView.setLayoutManager(new CommentsRecyclerViewLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCommentsAdapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showIssueDetail(String title, String body) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        mIssueBodyTextView.setText(body);

    }

    @Override
    public void showComments(List<Comment> commentList) {
        mCommentsAdapter.replaceData(commentList);
    }

    @Override
    public void setPresenter(IssueDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.send_comment_button)
    void onClickSendComment() {
        String commentText = mWriteCommentEditText.getText().toString();


        if (!StringUtil.isNullOrEmpty(commentText)) {
            mPresenter.postComment(commentText);
        }

    }
}
