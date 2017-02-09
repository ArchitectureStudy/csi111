package com.sean.android.mvpsample.data.issue;

import com.sean.android.mvpsample.base.model.ModelStateListener;

/**
 * Created by sean on 2017. 1. 7..
 */

public interface IssuesStateListener extends ModelStateListener<Issues>{

    @Override
    void onModelStateUpdated(Issues model);
}
