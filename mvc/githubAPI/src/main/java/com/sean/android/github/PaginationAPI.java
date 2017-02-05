package com.sean.android.github;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface PaginationAPI<T> {

    void asyncRequestNextPage(Callback<List<T>> callBack);

    List<T> requestNextPage();

    void matcherNextPage(Headers headers);

}
