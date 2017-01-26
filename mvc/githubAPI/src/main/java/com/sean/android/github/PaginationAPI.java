package com.sean.android.github;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by Seonil on 2017-01-25.
 */

public interface PaginationAPI<T> extends ItemsAPI<T> {

    <E extends List<T>> void asyncRequestNextPage(Callback callBack);

    List<T> requestNextPage();
}
