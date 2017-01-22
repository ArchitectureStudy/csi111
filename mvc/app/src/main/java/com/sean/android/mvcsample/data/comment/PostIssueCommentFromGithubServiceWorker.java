package com.sean.android.mvcsample.data.comment;

import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.base.util.GithubPreferenceKey;
import com.sean.android.mvcsample.base.util.SharedPreferencesService;
import com.sean.android.mvcsample.data.dto.CommentDTO;
import com.sean.android.mvcsample.data.issue.GithubUser;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2016-12-06.
 */

public class PostIssueCommentFromGithubServiceWorker extends ServiceWorker<CommentDTO> {

    private GithubUser githubUser;

    private int number;

    private String mCommentText;

    public PostIssueCommentFromGithubServiceWorker(GithubUser githubUser) {
        this(githubUser, -1, "");
    }

    public PostIssueCommentFromGithubServiceWorker(GithubUser githubUser, int number, String commentText) {
        this.githubUser = githubUser;
        this.number = number;
        this.mCommentText = commentText;
    }

    @Override
    protected Call<CommentDTO> createService() {
        PostCommentService postCommentService = retrofit.create(PostCommentService.class);

        if (githubUser != null) {
            return postCommentService.postComment(githubUser.getUser(), githubUser.getRepository(), number, createHeaderMap(), createBodyMap(mCommentText));
        }
        return null;
    }

    @Override
    protected String getURLAddress() {
        return "https://api.github.com";
    }

    public interface PostCommentService {

        @POST("/repos/{user}/{repository}/issues/{number}/comments")
        Call<CommentDTO> postComment(
                @Path("user") String user,
                @Path("repository") String repository,
                @Path("number") int number,
                @HeaderMap Map<String, String> headers,
                @Body Map<String, String> body);
    }

    private Map<String, String> createHeaderMap() {
        Map<String, String> headerMap = new HashMap<>();

        String token = SharedPreferencesService.getInstance().getPrefStringData(GithubPreferenceKey.PREF_GITHUB_ACCESS_TOKEN_KEY);

        headerMap.put("Authorization", "token " + token);

        return headerMap;
    }

    private Map<String, String> createBodyMap(String comment) {
        Map<String, String> bodyMap = new HashMap<>();


        bodyMap.put("body", comment);

        return bodyMap;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCommentText(String commentText) {
        this.mCommentText = commentText;
    }
}
