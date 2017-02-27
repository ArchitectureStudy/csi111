package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import android.view.View;

import com.sean.android.mvvmsample.base.databinding.BindableString;
import com.sean.android.mvvmsample.data.issue.Issue;

/**
 * Created by Seonil on 2017-02-27.
 */

public class IssueDetailViewModelImpl implements IssueDetailViewModel {
    private int mIssueNumber;
    private String mTitleText = "";
    private String mContentsText = "";
    private BindableString commentsText = new BindableString();

    public IssueDetailViewModelImpl() {
    }

    public IssueDetailViewModelImpl(Issue issue) {
        mTitleText = issue.getTitle();
        mContentsText = issue.getBody();
        mIssueNumber = issue.getNumber();
    }

    protected IssueDetailViewModelImpl(android.os.Parcel in) {
        mIssueNumber = in.readInt();
        mTitleText = in.readString();
        mContentsText = in.readString();
        commentsText = new BindableString();
        commentsText.set(in.readString());

    }

    public static final Creator<IssueDetailViewModelImpl> CREATOR = new Creator<IssueDetailViewModelImpl>() {
        @Override
        public IssueDetailViewModelImpl createFromParcel(android.os.Parcel in) {
            return new IssueDetailViewModelImpl(in);
        }

        @Override
        public IssueDetailViewModelImpl[] newArray(int size) {
            return new IssueDetailViewModelImpl[size];
        }
    };

    @Override
    public String getTitleText() {
        return mTitleText;
    }

    @Override
    public String getContentsText() {
        return mContentsText;
    }

    @Override
    public BindableString getCommentText() {
        return commentsText;
    }

    @Override
    public int getIssueNumber() {
        return mIssueNumber;
    }

    @Override
    public void onClickSendComment(View view) {

    }

    public void setTitleText(String titleText) {
        this.mTitleText = titleText;
    }

    public void setContentsText(String contentsText) {
        this.mContentsText = contentsText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeInt(mIssueNumber);
        parcel.writeString(mTitleText);
        parcel.writeString(mContentsText);
        parcel.writeString(commentsText.get());
    }
}