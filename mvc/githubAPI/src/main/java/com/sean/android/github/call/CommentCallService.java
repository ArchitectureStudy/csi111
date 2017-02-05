package com.sean.android.github.call;

import com.sean.android.github.dto.CommentDTO;

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

public interface CommentCallService {

    @GET("/repos/{user}/{repository}/issues/{number}/comments")
    Call<List<CommentDTO>> comments(@Path("user") String user, @Path("repository") String repository, @Path("number") int number);

    @POST("/repos/{user}/{repository}/issues/{number}/comments")
    Call<CommentDTO> postComment(
            @Path("user") String user,
            @Path("repository") String repository,
            @Path("number") int number,
            @HeaderMap Map<String, String> headers,
            @Body Map<String, String> body);
}
