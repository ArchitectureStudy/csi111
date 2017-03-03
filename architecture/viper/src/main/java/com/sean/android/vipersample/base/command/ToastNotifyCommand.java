package com.sean.android.vipersample.base.command;

import android.content.Context;

import com.sean.android.vipersample.base.util.ToastMaker;


/**
 * Created by Seonil on 2017-02-28.
 */

public class ToastNotifyCommand implements MessageNotifyCommand {

    private Context context;

    public ToastNotifyCommand(Context context) {
        this.context = context;
    }

    @Override
    public void execute(String message) {
        ToastMaker.makeShortToast(context, message);
    }
}
