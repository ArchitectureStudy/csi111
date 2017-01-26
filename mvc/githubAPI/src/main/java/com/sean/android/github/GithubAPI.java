package com.sean.android.github;

import com.sean.android.github.call.GithubAPICallService;
import com.sean.android.github.model.GithubConfiguration;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.sean.android.github.Config.PUBLIC_GITHUB_API_URL;

/**
 * Created by Seonil on 2017-01-24.
 */

public abstract class GithubAPI<T extends GithubAPICallService> {

    private Retrofit mRetrofit;

    protected Class<T> mCallClazz;

    private Map<String, String> mHeaderMap;

    public GithubAPI(Class<T> clazz) {
        mCallClazz = clazz;
        mRetrofit = createRetrofit();
        initializeHeaderMap();
    }

    private Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private void initializeHeaderMap() {
        if (mHeaderMap == null) {
            mHeaderMap = HeaderMatcher.pickHeaderListOut(GithubConfiguration.getInstance());
        }
    }

    protected T createCallService() {
        return mRetrofit.create(mCallClazz);
    }

    protected String getBaseURL() {
        return PUBLIC_GITHUB_API_URL;
    }

    protected Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void addHeader(String key, String value) {
        if (mHeaderMap != null) {
            mHeaderMap.put(key, value);
        }
    }

    public void removeHeader(String key) {
        if (mHeaderMap != null) {
            mHeaderMap.remove(key);
        }
    }

    public Map<String, String> getHeaderMap() {
        return mHeaderMap;
    }
}
