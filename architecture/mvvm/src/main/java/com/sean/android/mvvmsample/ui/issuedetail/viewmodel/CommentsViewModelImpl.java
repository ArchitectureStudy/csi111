package com.sean.android.mvvmsample.ui.issuedetail.viewmodel;

import com.sean.android.mvvmsample.base.viewmodel.NotifyUpdateViewModelListener;
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

public class CommentsViewModelImpl implements CommentsViewModel, CommentCommander {

    private int mIssueNumber;


    private PublishSubject<Comments> mPublishCommentsSubject;

    private Observable<List<CommentItemViewModel>> mCommentsObservable;

    private NotifyUpdateViewModelListener notifyUpdateViewModelListener;

    public CommentsViewModelImpl(int issueNumber) {
        this.mIssueNumber = issueNumber;
        mPublishCommentsSubject = PublishSubject.create();


        mCommentsObservable = mPublishCommentsSubject.onBackpressureLatest().map(new Func1<Comments, List<CommentItemViewModel>>() {
            @Override
            public List<CommentItemViewModel> call(Comments comments) {
                List<CommentItemViewModel> itemVMList = new ArrayList<>();
                for (Comment comment : comments.getModels()) {
                    itemVMList.add(new CommentItemViewModelImpl(comment));
                }
                return itemVMList;
            }
        });

    }

    @Override
    public void refreshComments() {
        fetchComments();
    }

    @Override
    public void fetchComments() {
        CommentsRepository.getInstance().getComments(mIssueNumber, new CommentsDataSource.LoadCommentsCallback() {
            @Override
            public void onCommentsLoaded(Comments comments) {
                List<CommentItemViewModel> itemVMList = new ArrayList<>();
                for (Comment comment : comments.getModels()) {
                    itemVMList.add(new CommentItemViewModelImpl(comment));
                }

                if (notifyUpdateViewModelListener != null) {
                    notifyUpdateViewModelListener.onUpdatedViewModel(itemVMList);
                }
            }

            @Override
            public void onCommentsFailed(int code, String message) {
            }
        });
    }

    @Override
    public boolean showIndicator() {
        return false;
    }

    @Override
    public void setUpdateViewModelListener(NotifyUpdateViewModelListener listener) {
        notifyUpdateViewModelListener = listener;
    }

    @Override
    public void refresh() {
        refreshComments();
    }


}
