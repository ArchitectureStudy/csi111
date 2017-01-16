package com.sean.android.mvcsample.base;

/**
 * Created by Seonil on 2017-01-16.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
}
