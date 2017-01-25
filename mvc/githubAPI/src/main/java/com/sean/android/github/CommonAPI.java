package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface CommonAPI<T> {
    List<T> callList(Callback callBack);

    T call(Callback callback);
}
