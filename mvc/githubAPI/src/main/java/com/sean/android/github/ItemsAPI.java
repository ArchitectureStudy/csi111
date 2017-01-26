package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface ItemsAPI<T> {
    <E extends List<T>> void asyncRequestItems(Callback<E> callBack);

    <T> void asyncRequestItem(Callback<T> callBack);

    List<T> requestItems();

    T requestItem();
}
