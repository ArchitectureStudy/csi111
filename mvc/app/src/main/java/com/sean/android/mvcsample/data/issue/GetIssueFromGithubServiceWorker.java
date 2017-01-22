package com.sean.android.mvcsample.data.issue;

import com.sean.android.mvcsample.base.network.ServiceWorker;
import com.sean.android.mvcsample.data.dto.IssueDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2016-12-06.
 */

public class GetIssueFromGithubServiceWorker extends ServiceWorker<List<IssueDTO>> {

    private GithubUser githubUser;

    private int number;

    public GetIssueFromGithubServiceWorker(GithubUser githubUser) {
        this(githubUser, -1);
    }

    public GetIssueFromGithubServiceWorker(GithubUser githubUser, int number) {
        this.githubUser = githubUser;
        this.number = number;
    }

    @Override
    protected Call<List<IssueDTO>> createService() {
        IssueListService issueListService = retrofit.create(IssueListService.class);

        if(githubUser != null) {
            return issueListService.issueList(githubUser.getUser(), githubUser.getRepository(), number);
        }
        return null;
    }

    @Override
    protected String getURLAddress() {
        return "https://api.github.com";
    }

    public interface IssueListService {

        @GET("/repos/{user}/{repository}/issues/{number}")
        Call<List<IssueDTO>> issueList(@Path("user") String user, @Path("repository") String repository, @Path("number") int number);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
