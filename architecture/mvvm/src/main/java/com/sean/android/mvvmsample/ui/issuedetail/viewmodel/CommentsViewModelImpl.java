package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import android.content.Context;

import com.sean.android.mvvmsample.base.util.ToastMaker;
import com.sean.android.mvvmsample.data.comment.Comment;
import com.sean.android.mvvmsample.data.comment.Comments;
import com.sean.android.mvvmsample.data.comment.CommentsDataSource;
import com.sean.android.mvvmsample.data.comment.CommentsRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by Seonil on 2017-02-27.
 */

public class CommentsViewModelImpl implements CommentsViewModel {

    private int issueNumber;

    private Context mContext;

    private PublishSubject<Comments> mPublishCommentsSubject;

    private Observable<List<CommentItemViewModel>> mCommentsObservable;

    public CommentsViewModelImpl(Context context, int issueNumber) {
        this.mContext = context;
        this.issueNumber = issueNumber;
        mPublishCommentsSubject = PublishSubject.create();


        mCommentsObservable = mPublishCommentsSubject.onBackpressureLatest().map(new Func1<Comments, List<CommentItemViewModel>>() {
            @Override
            public List<CommentItemViewModel> call(Comments comments) {
                List<CommentItemViewModel> itemVMList = new ArrayList<CommentItemViewModel>();
                for (Comment comment : comments.getModels()) {
                    itemVMList.add(new CommentItemViewModelImpl(comment));
                }
                return itemVMList;
            }
        });

    }

    @Override
    public void refreshComments() {

    }

    @Override
    public void fetchComments() {
        CommentsRepository.getInstance().getComments(issueNumber, new CommentsDataSource.LoadCommentsCallback() {
            @Override
            public void onCommentsLoaded(Comments comments) {
                mPublishCommentsSubject.onNext(comments);
            }

            @Override
            public void onCommentsFailed(int code, String message) {
                ToastMaker.makeShortToast(mContext, message);
            }
        });
    }

    @Override
    public boolean showIndicator() {
        return false;
    }

    @Override
    public Observable<List<CommentItemViewModel>> observComments() {
        return mCommentsObservable;
    }
}
