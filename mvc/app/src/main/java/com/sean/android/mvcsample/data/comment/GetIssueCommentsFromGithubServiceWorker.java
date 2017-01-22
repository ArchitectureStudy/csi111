package com.sean.android.mvcsample.data.comment;

import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.data.dto.CommentDTO;
import com.sean.android.mvcsample.data.dto.IssueDTO;
import com.sean.android.mvcsample.data.issue.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2016-12-06.
 */

public class GetIssueCommentsFromGithubServiceWorker extends ServiceWorker<List<CommentDTO>> {

    private GithubUser githubUser;

    private int number;

    public GetIssueCommentsFromGithubServiceWorker(GithubUser githubUser) {
        this(githubUser, -1);
    }

    public GetIssueCommentsFromGithubServiceWorker(GithubUser githubUser, int number) {
        this.githubUser = githubUser;
        this.number = number;
    }

    @Override
    protected Call<List<CommentDTO>> createService() {
        CommentListService  commentListService = retrofit.create(CommentListService .class);

        if(githubUser != null) {
            return commentListService.issueList(githubUser.getUser(), githubUser.getRepository(), number);
        }
        return null;
    }

    @Override
    protected String getURLAddress() {
        return "https://api.github.com";
    }

    public interface CommentListService {

        @GET("/repos/{user}/{repository}/issues/{number}/comments")
        Call<List<CommentDTO>> issueList(@Path("user") String user, @Path("repository") String repository, @Path("number") int number);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
