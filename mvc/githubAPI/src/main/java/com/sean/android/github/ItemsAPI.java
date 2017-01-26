package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface ItemsAPI<T> {
    void asyncRequestItems(Callback callBack);

    void asyncRequestItem(Callback callBack);

    List<T> requestItems();

    T requestItem();
}
