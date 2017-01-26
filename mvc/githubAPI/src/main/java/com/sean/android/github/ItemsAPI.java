package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface ItemsAPI<T> {
    <E extends List<T>> void asyncRequestItems(Callback callBack);

    <T> void asyncRequestItem(Callback callBack);

    List<T> requestItems();

    T requestItem();
}
