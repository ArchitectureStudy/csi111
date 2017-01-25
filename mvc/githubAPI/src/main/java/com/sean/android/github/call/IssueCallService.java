package com.sean.android.github.call;

import com.sean.android.github.dto.IssueDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface IssueCallService extends GithubAPICallService {

    @GET("/repos/{user}/{repository}/issues")
    Call<List<IssueDTO>> issues(@Path("user") String user, @Path("repository") String repository);

    @GET("/repos/{user}/{repository}/issues/{number}")
    Call<IssueDTO> issue(@Path("user") String user, @Path("repository") String repository, @Path("number") int number);
}
