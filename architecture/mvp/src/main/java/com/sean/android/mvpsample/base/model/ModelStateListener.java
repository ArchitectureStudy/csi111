package com.sean.android.mvpsample.base.model;

/**
 * Created by sean on 2017. 1. 7..
 */

public interface ModelStateListener<T extends Model> {
    void onModelStateUpdated(T model);
}
