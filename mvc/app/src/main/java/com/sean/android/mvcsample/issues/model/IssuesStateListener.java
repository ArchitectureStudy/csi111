package com.sean.android.mvcsample.issues.model;

import com.sean.android.mvcsample.base.model.Model;
import com.sean.android.mvcsample.base.model.ModelStateListener;

/**
 * Created by sean on 2017. 1. 7..
 */

public interface IssuesStateListener extends ModelStateListener<Issues>{

    @Override
    void onModelStateUpdated(Issues model);
}
