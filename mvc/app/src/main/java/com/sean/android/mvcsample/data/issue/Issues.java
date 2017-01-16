package com.sean.android.mvcsample.data.issue;

import com.sean.android.mvcsample.base.model.ModelContainer;

/**
 * Created by sean on 2017. 1. 7..
 */

public class Issues extends ModelContainer<Issue> {

    @Override
    public void add(Issue model) {
        super.add(model);
        if(modelStateListener != null) {
            modelStateListener.onModelStateUpdated(this);
        }
    }



}
