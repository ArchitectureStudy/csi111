package com.sean.android.mvcsample.data.comment;

import android.support.annotation.NonNull;

import com.sean.android.mvcsample.base.network.HttpResponseData;
import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.base.util.GithubPreferenceKey;
import com.sean.android.mvcsample.base.util.SharedPreferencesService;
import com.sean.android.mvcsample.data.dto.CommentDTO;
import com.sean.android.mvcsample.data.issue.GithubUser;

import java.util.List;

/**
 * Created by sean on 2017. 1. 21..
 */

public class CommentsRepository implements CommentsDataSource {
    private volatile static CommentsRepository instance = null;

    private GetIssueCommentsFromGithubServiceWorker mGetIssueCommentsFromGithubServiceWorker;

    private PostIssueCommentFromGithubServiceWorker mPostIssueCommentFromGithubServiceWorker;


    public static CommentsRepository getInstance() {
        if (instance == null) {
            synchronized (CommentsRepository.class) {
                if (instance == null) {
                    instance = new CommentsRepository();
                }
            }
        }

        return instance;
    }

    private CommentsRepository() {
        this(new GithubUser(SharedPreferencesService.getInstance().getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ID_KEY), SharedPreferencesService.getInstance().getPrefStringData(GithubPreferenceKey.PREF_GITHUB_REPOSITORY_KEY)));
    }

    private CommentsRepository(GithubUser githubUser) {
        mGetIssueCommentsFromGithubServiceWorker = new GetIssueCommentsFromGithubServiceWorker(githubUser);
        mPostIssueCommentFromGithubServiceWorker = new PostIssueCommentFromGithubServiceWorker(githubUser);
    }

    @Override
    public void getComments(int issueNumber, @NonNull LoadCommentsCallback callback) {
        executeCommentsService(issueNumber, callback);
    }

    @Override
    public void postComments(int number, String body, @NonNull final PostCommentCallback callback) {
        mPostIssueCommentFromGithubServiceWorker.setNumber(number);
        mPostIssueCommentFromGithubServiceWorker.setCommentText(body);
        mPostIssueCommentFromGithubServiceWorker.setServiceWorkEventListener(new ServiceWorker.ServiceWorkEventListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onComplete(HttpResponseData result) {
                if(result.getResponseData() instanceof CommentDTO) {
                    callback.onCommentPosted(Comment.convertModel((CommentDTO) result.getResponseData()));
                }
            }

            @Override
            public void onFail(Throwable e) {
                callback.onCommentPostFailed();
            }
        });
        mPostIssueCommentFromGithubServiceWorker.executeAsync();

    }


    private void executeCommentsService(int issueNumber, final LoadCommentsCallback loadCommentsCallback) {
        mGetIssueCommentsFromGithubServiceWorker.setNumber(issueNumber);
        mGetIssueCommentsFromGithubServiceWorker.setServiceWorkEventListener(new ServiceWorker.ServiceWorkEventListener() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onComplete(HttpResponseData result) {
                Comments comments = new Comments();
                if (result.getResponseData() instanceof List) {
                    List<CommentDTO> coomentsDTO = (List<CommentDTO>) result.getResponseData();
                    for (CommentDTO commentDTO : coomentsDTO) {
                        comments.add(Comment.convertModel(commentDTO));
                    }
                }

                loadCommentsCallback.onCommentsLoaded(comments);
            }

            @Override
            public void onFail(Throwable e) {
                loadCommentsCallback.onCommentsFailed();
            }
        });
        mGetIssueCommentsFromGithubServiceWorker.executeAsync();
    }
}
