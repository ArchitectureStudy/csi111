package com.sean.android.github.call;

import com.sean.android.github.dto.IssueDTO;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface IssueCallService<T> extends GithubAPICallService {

    @GET("{fullPath}")
    Call<List<T>> issues(@HeaderMap Map<String, String> headers, @Path(value = "fullPath", encoded = true) String fullPath);

    @GET("/repos/{owner}/{repository}/issues")
    Call<List<T>> issues(@Path("owner") String owner, @Path("repository") String repository);

    @GET("/orgs/{orgs}/issues")
    Call<List<T>> issues(@Path("orgs") String organization);

    @GET("/repos/{owner}/{repository}/issues/{number}")
    Call<T> issue(@Path("owner") String owner, @Path("repository") String repository, @Path("number") int number);

    @POST("/repos/{owner}/{repository}/issues")
    Call<T> postIssue(@Path("owner") String owner, @Path("repository") String repository, @Body IssueDTO issueDTO);
}
