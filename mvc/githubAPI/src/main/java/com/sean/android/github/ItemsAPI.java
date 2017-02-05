package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface ItemsAPI<T, E extends List<T>> {
    void asyncRequestItems(String owner, String repository, Callback<E> callBack);

    void asyncRequestItem(String owner, String repository, int number, Callback<T> callBack);

    List<T> requestItems(String owner, String repository);

    T requestItem(String owner, String repository, int number);
}
