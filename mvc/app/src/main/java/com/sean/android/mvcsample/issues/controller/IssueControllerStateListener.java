package com.sean.android.mvcsample.issues.controller;

import com.sean.android.mvcsample.issues.model.Issues;

/**
 * Created by sean on 2017. 1. 7..
 */

public interface IssueControllerStateListener {

    void updateModelStarted();
    void updateModelFinished();
    void updateModelFailed();
}
