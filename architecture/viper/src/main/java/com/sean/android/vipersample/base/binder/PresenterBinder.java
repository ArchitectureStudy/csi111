package com.sean.android.vipersample.base.binder;

/**
 * Created by Seonil on 2017-03-06.
 */

public interface PresenterBinder<T> {

    void bindPresenter(T presenter);

    void unbindPresenter();

}
