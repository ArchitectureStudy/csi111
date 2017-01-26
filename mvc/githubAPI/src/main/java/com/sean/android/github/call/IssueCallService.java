package com.sean.android.github.call;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface IssueCallService<T> extends GithubAPICallService {

    @GET("{fullPath}")
    Call<List<T>> issues(@HeaderMap Map<String, String> headers, @Path(value = "fullPath", encoded = true) String fullPath);

    @GET("/repos/{user}/{repository}/issues")
    Call<List<T>> issues(@Path("user") String user, @Path("repository") String repository);

    @GET("/repos/{user}/{repository}/issues/{number}")
    Call<T> issue(@Path("user") String user, @Path("repository") String repository, @Path("number") int number);
}
