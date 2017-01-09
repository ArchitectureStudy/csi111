package com.sean.android.mvcsample.issues.model;

import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.issues.model.dto.IssueDTO;
import com.sean.android.mvcsample.issues.model.dto.IssuesDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2016-12-06.
 */

public class GetIssuesFromGithubServiceWorker extends ServiceWorker<List<IssueDTO>> {

    private GithubUser githubUser;

    public GetIssuesFromGithubServiceWorker(GithubUser githubUser) {
        this.githubUser = githubUser;
    }

    @Override
    protected Call<List<IssueDTO>> createService() {
        IssueListService issueListService = retrofit.create(IssueListService.class);

        if(githubUser != null) {
            return issueListService.issueList(githubUser.getUser(), githubUser.getRepository());
        }

        return null;
    }

    @Override
    protected String getURLAddress() {
        return "https://api.github.com";
    }

    public interface IssueListService {

        @GET("/repos/{user}/{repository}/issues")
        Call<List<IssueDTO>> issueList(@Path("user") String user, @Path("repository") String repository);
    }
}
