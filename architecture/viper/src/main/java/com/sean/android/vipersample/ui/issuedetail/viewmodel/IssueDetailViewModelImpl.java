package com.sean.android.vipersample.ui.issuedetail.viewmodel;

import com.sean.android.vipersample.base.databinding.BindableString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seonil on 2017-02-27.
 */

public class IssueDetailViewModelImpl implements IssueDetailViewModel {

    private BindableString mTitleText = new BindableString();
    private BindableString mContentsText = new BindableString();
    private BindableString mCommentsText = new BindableString();

    private List<CommentCommander> mCommanders = new ArrayList<>();

    public IssueDetailViewModelImpl() {
        this("", "", "");
    }

    public IssueDetailViewModelImpl(String titleText, String contentsText) {
        this(titleText, contentsText, "");
    }

    public IssueDetailViewModelImpl(String titleText, String contentsText, String commentsText) {
        this.mTitleText.set(titleText);
        this.mContentsText.set(contentsText);
        this.mCommentsText.set(commentsText);
    }

    protected IssueDetailViewModelImpl(android.os.Parcel in) {
        mTitleText = new BindableString();
        mTitleText.set(in.readString());
        mContentsText = new BindableString();
        mContentsText.set(in.readString());
        mCommentsText = new BindableString();
        mCommentsText.set(in.readString());

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
    public BindableString getTitleText() {
        return mTitleText;
    }

    @Override
    public BindableString getContentsText() {
        return mContentsText;
    }

    @Override
    public BindableString getCommentText() {
        return mCommentsText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(mTitleText.get());
        parcel.writeString(mContentsText.get());
        parcel.writeString(mCommentsText.get());
    }

    private void commandRefreshAll() {
        if (mCommanders != null && mCommanders.size() > 0) {
            for (CommentCommander commander : mCommanders) {
                commander.refresh();
            }
        }
    }
}
