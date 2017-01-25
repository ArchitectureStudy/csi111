package com.sean.android.github;

import com.sean.android.github.call.GithubAPICallService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sean.android.github.Config.PUBLIC_GITHUB_API_URL;

/**
 * Created by Seonil on 2017-01-24.
 */

public class GithubAPI<T extends GithubAPICallService> {

    protected Retrofit retrofit;

    protected Class<T> callClazz;

    public GithubAPI(Class<T> clazz) {
        callClazz = clazz;
        retrofit = createRetrofit();
    }

    protected Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PUBLIC_GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    protected T createCallService() {
        return retrofit.create(callClazz);
    }
}
