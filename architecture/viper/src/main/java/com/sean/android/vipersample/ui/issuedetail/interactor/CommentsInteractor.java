package com.sean.android.vipersample.ui.issuedetail.interactor;

import com.sean.android.vipersample.data.comment.Comment;
import com.sean.android.vipersample.data.comment.Comments;
import com.sean.android.vipersample.data.comment.CommentsDataSource;
import com.sean.android.vipersample.data.comment.CommentsRepository;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModel;
import com.sean.android.vipersample.ui.issuedetail.viewmodel.CommentItemViewModelImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seonil on 2017-03-06.
 */

public class CommentsInteractor {

    private int mIssueNumber;

    public CommentsInteractor(int issueNumber) {
        this.mIssueNumber = issueNumber;
    }

    public void execute(final Action action) {
        CommentsRepository.getInstance().getComments(mIssueNumber, new CommentsDataSource.LoadCommentsCallback() {
            @Override
            public void onCommentsLoaded(Comments comments) {
                List<CommentItemViewModel> itemVMList = new ArrayList<>();
                for (Comment comment : comments.getModels()) {
                    itemVMList.add(new CommentItemViewModelImpl(comment));
                }

                if (action != null) {
                    action.onCompleted(itemVMList);
                }

            }

            @Override
            public void onCommentsFailed(int code, String message) {
                if (action != null) {
                    action.onFailed(message);
                }
            }
        });
    }


    public interface Action {
        void onCompleted(List<CommentItemViewModel> itemViewModelList);

        void onFailed(String message);
    }
}
